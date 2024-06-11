package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    //CRUD Operations Logic
    @Autowired
    private DoctorRepository doctorRep;

    public List<Doctor> getAllDoctors(){
        return doctorRep.findAll();
    }

    public Doctor getDoctorById(Integer id){
        return doctorRep.findById(id).orElse(null);
    }

    public Result createDoctor(Doctor doctor){
        System.out.println("Doctor : " + doctor.toString());
        if (doctor.hasEmptyOrNull()){
            return new Result(1, "One or more fields empty/null");
//            return new Result("One or more fields empty/null", 1);
        }

        //handling exception
        try{
            doctorRep.save(doctor);
        }catch (Exception e){
            return new Result(1, "Exception during doctor save");
//            return new Result("Exception during doctor save",1);
        }

        return new Result(0, "Doctor created successfully");
//        return new Result("Doctor created successfully", 0);
    }

    //Thodoris edit
    //DONE : log in endpoint + CORRECT
    public Result logIN(String username, String password){
        Doctor doc = doctorRep.findByDocUsernameAndPassword(username, password);
//        System.out.println("Doctor interface : " + doc.toString());
//        Result res = new Result();  //TEST
        if(doc == null){
            System.out.println("userExists == FALSE");
//            res = new Result(1, "User doesn't exist. Check your username and password");
            return new Result(1, "User doesn't exist. Check your username and password");
//            return new Result("User doesn't exist. Check your username and password", 1);
        }

        UserSession userSession = new UserSession(doc);
        boolean userExists = userSession.tryLogIn();
        System.out.println("The User Is : " + userExists + " " + userSession.getDocSessionJSON());
        System.out.println("Resutl User session print : ");
//        res.resultJSON(0, userSession);  //TEST
//        return res; //TEST
        return new Result(0, userSession.getDocSessionJSON());  //initial way
//        return new Result(userSession.getDocSessionJSON(), 0);
    }

//    public Doctor updateDoctor(Doctor doctor){
//        return doctorRep.save(doctor);
//    }
//
//    public void deleteDoctor(Integer id){
//        doctorRep.deleteById(id);
//    }
}

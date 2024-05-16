package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRep;

    //CRUD Operations Declaration

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
        }

        //handling exception
        try{
            doctorRep.save(doctor);
        }catch (Exception e){
            return new Result(1, "Exception during doctor save");
        }

        return new Result(0, "Doctor created successfully");
    }


//    public Result logIn(Integer id, String username, String password){
//        //finding a specific doctor by id
//        Doctor doc = doctorRep.getReferenceById(id);
//        UserSession userSession = new UserSession();
//        //checking if the doctor with the specific id matches the username and password
//        if(doc.getDocUsername().equals(username) && doc.getPassword().equals(password)){
//            return new Result(0, userSession.getAllFields());
//        }
//        return new Result(1, "User Session Info In JSON Format");
//    }

    //Thodoris edit
    //TODO : log in endpoint
    public Result logIN(String docUsername, String password){
        //TODO : check if the specific user has an entry in the db
        UserSession userSession = new UserSession(docUsername, password);
        boolean userExists = userSession.tryLogIn();
        if(!userExists){
            return new Result(1, "User don't exist. Check yur username and password");
        }
        System.out.println("The User Is : " + userExists + " " + userSession.getSessionJSON());
        return new Result(0, userSession.getSessionJSON());
    }




    // TEST METHOD To see the data that are sending wright
//    public void newUserDoc(String fName, String lName, String username, String password, String email, String amka,
//                              String phoneNumber, String profession, String address){
//        Doctor d = new Doctor(fName,lName,username, password, email, amka, phoneNumber, profession, address);
//        System.out.println("------- TEST PRING ON SERVICE CLASS -------");
//        System.out.println(d.toString());
//    }

//    public Doctor updateDoctor(Doctor doctor){
//        return doctorRep.save(doctor);
//    }
//
//    public void deleteDoctor(Integer id){
//        doctorRep.deleteById(id);
//    }
//
//    //TEST METHOD - ERROR STATUS 500
//    public Doctor getDoctor() {
//        Doctor doc = new Doctor("Antouela", "Bitsa", "kadakjdl", "6983805369", "antou@gmail.com", "5684623", "adanon", "programmer","jkjnkjkn");
//        return doctorRep.save(doc);
//    }
}

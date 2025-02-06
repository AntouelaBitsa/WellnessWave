package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    //CRUD Operations Logic
    @Autowired
    private DoctorRepository doctorRep;

    public List<Doctor> getAllDoctors(){
        return doctorRep.findAll();
    }

    public Doctor getDoctorById(Integer id){
        return doctorRep.findById(id).orElseThrow(()-> new RuntimeException("Doctor Not Found."));
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

    //Thodoris edit
    //DONE : log in endpoint + CORRECT
    public Result logIN(String username, String password){
        Doctor doc = doctorRep.findByDocUsernameAndPassword(username, password);
//        System.out.println("Doctor interface : " + doc.toString());
//        Result res = new Result();  //TEST
        if(doc == null){
            System.out.println("userExists == FALSE");
            return new Result(1, "User doesn't exist. Check your username and password");
        }

        UserSession userSession = new UserSession(doc);
        boolean userExists = userSession.tryLogIn();
        System.out.println("The User Is : " + userExists + " " + userSession.getDocSessionJSON());
        System.out.println("Resutl User session print : ");
//        res.resultJSON(0, userSession);  //TEST
//        return res; //TEST
        return new Result(0, userSession.getDocSessionJSON());  //initial way
    }

    public Doctor updateDoctor(Integer id, Map<String, Object> updatedDoc){
        Doctor mappedDoctorById = this.getDoctorById(id);
        System.out.println("[Debug Message: Update Info] Mapped Patient Id: " + mappedDoctorById);

        if (mappedDoctorById.hasEmptyOrNull() || mappedDoctorById == null){
            System.out.println("Something went wrong in patient retrieval, some field is null or the patient doesn't exists");
        }
        System.out.println("Keys in updatedDocMap: " + updatedDoc.keySet());
        System.out.println("[Service before mapping] updatedDoc: " + updatedDoc.toString());

        updatedDoc.forEach((key, value) -> {
            Field fieldToUpdate = ReflectionUtils.findField(Doctor.class, key);
//            System.out.println("[Service Mapping] fieldsToUpdate: " + fieldToUpdate.toString());
            if (fieldToUpdate == null){
                System.out.println("The Filed: " + key.toString() + " & in empty or null");
                return;
            }
            fieldToUpdate.setAccessible(true);
//            ReflectionUtils.setField(fieldToUpdate, mappedDoctorById, value);

            // Check if the field type matches the value type
            if (fieldToUpdate.getType().isAssignableFrom(value.getClass())) {
                ReflectionUtils.setField(fieldToUpdate, mappedDoctorById, value);
            } else {
                System.out.println("Field to Update type" + fieldToUpdate.getType().isAssignableFrom(value.getClass()));
                System.out.println("Type mismatch: " + key.toString() + " expects " + fieldToUpdate.getType() + " but got " + value.getClass());
            }
        });

        return doctorRep.save(mappedDoctorById);
    }

    public void deleteDoctor(Integer id){
        Doctor doctorToDelete = doctorRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));

        try {
            System.out.println("[1 - On Doc Delete] Inside try before Delete: " + doctorToDelete);
            doctorRep.deleteById(id);
//            System.out.println("[2 - On Doc Delete] Inside try before Delete: " + doctorRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor Not Found")));
        }catch (Exception e){
            System.out.println("[3 - On Doc Delete] Exception Occurred: " + e.getMessage());
        }

    }
}

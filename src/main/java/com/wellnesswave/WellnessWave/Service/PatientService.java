package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.PatientRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.util.Supplier;
import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {
    //CRUD Operations Logic
    @Autowired
    private PatientRepository patientRep;

    public List<Patient> getAllPatients(){
        return patientRep.findAll();
    }

    public Patient getPatientById(Integer id){
        return patientRep.findById(id).orElseThrow(()-> new RuntimeException("Patient Not Found."));
    }
    //DONE: createPatient
    public Result createPatient(Patient patient) {
        System.out.println("Patient : " + patient.toString());
        if (patient.hasEmptyOrNull()) {
            return new Result(1, "One or more fields empty/null");
        }

        try {
            patientRep.save(patient);
        }catch (Exception e){
            return new Result(1, "Exception during patient save");
        }
        return new Result(0, "Patient created successfully");
    }

    //TODO: logIN Patient
    public Result logIN(String patUsername, String patPassword){
        Patient pat = patientRep.findByPatUsernameAndPatPassword(patUsername, patPassword);
        if (pat == null){
            System.out.println("userExists == FALSE");
            return new Result(1, "User doesn't exist. Check your username and password");
        }
        //TODO : check if the specific patient has an entry in the db
        //TODO : MODIFICATIONS IN USER SESSION CLASS
        UserSession userSession = new UserSession(pat);
        boolean userExists = userSession.tryLogIn();
        System.out.println("The User Is : " + userExists + " " + userSession.getDocSessionJSON());
        System.out.println("Resutl User session print : ");
        return new Result(0, userSession.getPatSessionJSON());
    }

    public Patient updatePatient(Integer id, Map<String, Object> updatedPat){
        Patient mappedPatientById = this.getPatientById(id);
        System.out.println("[Debug Message: Update Info] Mapped Patient Id: " + mappedPatientById);

        if (mappedPatientById.hasEmptyOrNull() || mappedPatientById == null){
            System.out.println("Something went wrong in patient retrieval, some field is null or the patient doesn't exists");
        }

        updatedPat.forEach((key, value) -> {
            Field fieldToUpdate = ReflectionUtils.findField(Patient.class, key);
            if (fieldToUpdate == null){
                System.out.println("The Filed: " + key + " & in empty or null");
            }
            fieldToUpdate.setAccessible(true);
//            ReflectionUtils.setField(fieldToUpdate, mappedPatientById, value);

            // Check if the field type matches the value type
            if (fieldToUpdate.getType().isAssignableFrom(value.getClass())) {
                ReflectionUtils.setField(fieldToUpdate, mappedPatientById, value);
            } else {
                System.out.println("Field to Update type" + fieldToUpdate.getType().isAssignableFrom(value.getClass()));
                System.out.println("Type mismatch: " + key.toString() + " expects " + fieldToUpdate.getType() + " but got " + value.getClass());
            }
        });

        return patientRep.save(mappedPatientById);
    }

//    public boolean deletePatient2(Integer id){
//        if(id.equals(null)) {
//            return false;
//        }
//        Patient patientToDel = this.getPatientById(id);
//        System.out.println("PatientToDelete: " + patientToDel);
//        patientToDel.setAccountStatus("Inactive");
//        System.out.println("PatientToDelete Status: " + patientToDel.getAccountStatus());
//        //TODO: Check id
//        try{
//            patientRep.save(patientToDel);
//            System.out.println("PatientToDelete Inside TRY: " + patientRep.save(patientToDel));
//        }catch (Exception e){
//            System.out.println(">>> Exception on soft delete : " + e.getMessage());
//            return false;
//        }
//
//        return true;
//    }

    public void deletePatient(Integer id){
        Patient patientToDelete = patientRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));

        try {
            System.out.println("[1 - On Pat Delete] Inside try before Delete: " + patientToDelete);
            patientRep.deleteById(id);
//            System.out.println("[2 - On Doc Delete] Inside try before Delete: " + doctorRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor Not Found")));
        }catch (Exception e){
            System.out.println("[3 - On Pat Delete] Exception Occurred: " + e.getMessage());
        }

    }

//    public Patient getPatByAmka(String amka){
//        Patient p;
//
//        try{
//            p = patientRep.findByPatAmka(amka);
//        }catch (Exception e){
//            System.out.println(">PatientService: getPatByAmka<" +
//                    "An exception occurred: " + e + "Patient setted to null");
//            p = new Patient();
//        }
//
//        return p;
//    }
}

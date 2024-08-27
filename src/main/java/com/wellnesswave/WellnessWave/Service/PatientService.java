package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.PatientRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import com.wellnesswave.WellnessWave.Utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    //CRUD Operations Logic
    @Autowired
    private PatientRepository patientRep;

    public List<Patient> getAllPatients(){
        return patientRep.findAll();
    }

    public Patient getPatientById(Integer id){
        return patientRep.findById(id).orElse(null);
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

//    public Patient updatePatient(Patient patient){
//        return patientRep.save(patient);
//    }
//
//    public void deletePatient(Integer id){
//        patientRep.deleteById(id);
//    }
}

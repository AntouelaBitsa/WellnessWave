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
//    public Result logIN(String patUsername, String patPassword){
//        //TODO : check if the specific patient has an entry in the db
//        //TODO : MODIFICATIONS IN USERSESSION CLASS
//        UserSession userSession = new UserSession(patUsername, patPassword);
//        boolean userExists = userSession.tryLogIn();
//        if(!userExists){
//            return new Result(1, "Patient doesn't exist! Please insert valid Username and Password");
//        }
//        System.out.println("The User Is : " + userExists + " " + userSession.getPatSessionJSON());
//        return new Result(0, userSession.getPatSessionJSON());
//    }

//    public Patient updatePatient(Patient patient){
//        return patientRep.save(patient);
//    }
//
//    public void deletePatient(Integer id){
//        patientRep.deleteById(id);
//    }
}

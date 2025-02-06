package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Service.PatientService;
import com.wellnesswave.WellnessWave.Utils.Result;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class PatientController {
    //CRUD Operations Implemenation
    @Autowired
    private PatientService patientService;

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/getPatientById/{id}")
    public Patient getPatientById(@PathVariable Integer id){
        return patientService.getPatientById(id);
    }

    //DONE: createPatient endpoint
    @PostMapping("/createPatient")
    public ResponseEntity<Result> createPatient(@RequestBody Patient patient){
        //Start Testing Print
        System.out.println("//** Test Print : Controller **//");
        System.out.println(patient.toString());
        //End of Testing Print
        return new ResponseEntity<Result>(patientService.createPatient(patient), HttpStatus.CREATED);
    }

    //DONE: logIn endpoint
    @PostMapping("/patientLogInSession")
    public ResponseEntity<Result> logIn(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("Before doctor service log in ");
        Result result = patientService.logIN(username, password);

        System.out.println(">> Print of result in controller -> " + result.toString()); //TEST
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Map<String, Object> updatedPatMap){
        System.out.println("[Controller: Update Patient] Id: " + id + " UpdatedPatient: " + updatedPatMap.toString());
        Patient updatedPat = patientService.updatePatient(id, updatedPatMap);
        return new ResponseEntity<>(updatedPat, HttpStatus.OK);
    }

//    @PostMapping("/deletePatient/{id}")
//    public ResponseEntity<Result> deletePatient2(@PathVariable("id") Integer id){
//        boolean patDeleted = patientService.deletePatient2(id);
//        Result result;
//        if (!patDeleted){
//            result = new Result(1, "User not found");
//            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//        }
//        result = new Result(0, "User deleted Successfully");
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Result> deletePatient(@PathVariable Integer id){

        try{
            patientService.deletePatient(id);
            System.out.println("[1 - Controller: On pat Deletion] " + "Patient with ID: " + id + " deleted successfully.");
            Result success = new Result(1, "Patient with ID: " + id + " deleted successfully.");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }catch(EntityNotFoundException entityNotFound){
            System.out.println("[2 - Controller: On pat Deletion] " + "Patient with ID: " + id + " not found.");
            Result entityNFound = new Result(0, "Patient with ID: " + id + " not found.");
            return new ResponseEntity<>(entityNFound, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            System.out.println("[3 - Controller: On pat Deletion] " + "An error occurred while deleting the patient.");
            Result exception = new Result(0, "An error occurred while deleting the patient.");
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

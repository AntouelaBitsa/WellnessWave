package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Service.PatientService;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @PostMapping("/updatePatient")
//    public Patient updatePatient(@PathVariable Patient patient){
//        return patientService.updatePatient(patient);
//    }
//
//    @DeleteMapping("/deletePatient")
//    public void deletePatient(@PathVariable Integer id){
//        patientService.deletePatient(id);
//    }
}

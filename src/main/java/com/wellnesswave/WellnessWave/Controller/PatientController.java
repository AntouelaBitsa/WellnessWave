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
//    @PostMapping("/patientLogInSession")
//    public Result logIn(@RequestParam("username") String username, @RequestParam("password") String password){
//        patientService.logIN(username, password);
//        if(username.equals(null) && password.equals(null)){
//            System.out.println("Failed to find patient");
//            return new Result(1, " Failed to find patient");
//        }
//        System.out.println("User Session info in JSON Format");
//        return new Result(0, "User Session info in JSON Format");
//    }

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

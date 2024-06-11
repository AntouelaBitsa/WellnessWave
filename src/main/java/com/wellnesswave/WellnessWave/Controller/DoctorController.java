package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Service.DoctorService;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    //CRUD Operations Implemenation
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors(){
        //WITH POSTMAN WORKS
        //TESTING
//        Doctor doc = new Doctor("Antouela", "Bitsa", "kadakjdl", "6983805369", "antou@gmail.com", "5684623", "adanon", "programmer");
//        return doc;
        return doctorService.getAllDoctors();
    }

    @GetMapping("/getDoctorById/{id}")
    public Doctor getDoctorById(@PathVariable Integer id){
        //WITH POSTMAN WORKS
        System.out.println("//TEST Print// " + doctorService.getDoctorById(id));
        return doctorService.getDoctorById(id);
    }

    //CORRECT: with result class -> returns 0/1 for successful/failure
    @PostMapping("/createDoctor")
    public ResponseEntity<Result> createDoctor(@RequestBody Doctor doctor) {
        //Start : Testing Purpose
        System.out.println("//** TEST PRINT : CONTROLLER **//");
        System.out.println(doctor.toString());
        //End : Testing Purpose
        return new ResponseEntity<Result>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    //DONE : log in endpoint and add annotation + CORRECT
        @PostMapping("/doctorLogInSession")
        public ResponseEntity<Result> logIN(@RequestParam("username") String username, @RequestParam("password") String password){
            System.out.println("Before doctor service log in ");
            Result result = doctorService.logIN(username, password);
            System.out.println(">> Print of result in controller -> " + result.toString()); //TEST
//            return doctorService.logIN(username, password);
            return new ResponseEntity<>(result, HttpStatus.OK);


            //        if(username.equals(null) && password.equals(null)){
            //            System.out.println("Failed to find doctor");
            //            return new Result(1, "Failed to find doctor");
            //        }
            //        System.out.println("User Session Info In JSON Format");
            //        return new Result(0, new UserSession().getSessionJSON());

        }

//
//    @PostMapping("/updateDoctor")
//    public Doctor updateDoctor(@PathVariable Doctor doctor){
//        return doctorService.updateDoctor(doctor);
//    }
//
//    @DeleteMapping("/deleteDoctor")
//    public void deleteDoctor(@PathVariable Integer id){
//        doctorService.deleteDoctor(id);
//    }
//
//    //check for other endpoints that we want to implement
}

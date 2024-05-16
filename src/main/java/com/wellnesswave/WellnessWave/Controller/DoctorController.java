package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Service.DoctorService;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
public class DoctorController {
    //CRUD Operations Implemenation
    @Autowired
    private DoctorService doctorService;

    //TEST METHOD - ERROR STATUS 500
//    @GetMapping("/showDoc")
//    public String showDoc(){
////        return doctorService.getDoctor(); -> error 500
//
//        //thses 2 lines works perfectly
//        Doctor doc = new Doctor("Antouela", "Bitsa", "kadakjdl", "6983805369",
//                "antou@gmail.com", "5684623", "adanon", "programmer","lknkljiijk");
//        return doc.toString();
////        return doc; -> error 500
//    }

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

    //corrected with result class -> returns 0/1 for successful/failure
    @PostMapping("/createDoctor")
    public ResponseEntity<Result> createDoctor(@RequestBody Doctor doctor) {
        //Start : Testing Purpose
        System.out.println("//** TEST PRINT : CONTROLLER **//");
        System.out.println(doctor.toString());
        //End : Testing Purpose
        return new ResponseEntity<Result>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    //TODO : log in endpoint and add annotation
    @PostMapping("/logInSession")
    public Result logIn(@RequestParam("username") String username, @RequestParam("password") String password){
        doctorService.logIN(username, password);
        if(username.equals(null) && password.equals(null)){
            System.out.println("Failed to find doctor");
            return new Result(1, "Failed to find doctor");
        }
        System.out.println("User Session Info In JSON Format");
        return new Result(0, "User Session Info In JSON Format");
    }

    // TEST METHOD To see the data that are sending wright
//    @PostMapping("/newUser")
//    public void newUser(@RequestParam String fName,
//                        @RequestParam String lName,
//                        @RequestParam String username,
//                        @RequestParam String password,
//                        @RequestParam String email,
//                        @RequestParam String amka,
//                        @RequestParam String phoneNumber,
//                        @RequestParam String profession,
//                        @RequestParam String address){
//        Doctor d = new Doctor(fName,lName,username, password, email, amka, phoneNumber, profession, address);
//        System.out.println("------- TEST PRINt ON Controller CLASS -------");
//        System.out.println(d.toString());
//    }


    //TEST METHOD - ERROR STATUS 500
//    @PostMapping("/addDoctor")
//    public Doctor addDoctor(@RequestBody Doctor doc){
//        return doctorService.createDoctor(doc);
//    }
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

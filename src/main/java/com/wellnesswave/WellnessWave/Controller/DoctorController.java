package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Service.DoctorService;
import com.wellnesswave.WellnessWave.Utils.Result;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/updateDoctor/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id, @RequestBody Map<String, Object> updatedDocMap){
        System.out.println("[Controller: Update Patient] Id: " + id + " UpdatedPatient: " + updatedDocMap.toString());
        Doctor updatedDoc = doctorService.updateDoctor(id, updatedDocMap);
        return new ResponseEntity<>(updatedDoc, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<Result> deleteDoctor(@PathVariable Integer id){

        try{
            doctorService.deleteDoctor(id);
            System.out.println("[1 - Controller: On doc Deletion] " + "Doctor with ID: " + id + " deleted successfully.");
            Result success = new Result(1, "Doctor with ID: " + id + " deleted successfully.");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }catch(EntityNotFoundException entityNotFound){
            System.out.println("[2 - Controller: On doc Deletion] " + "Doctor with ID: " + id + " not found.");
            Result entityNFound = new Result(0, "Doctor with ID: " + id + " not found.");
            return new ResponseEntity<>(entityNFound, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            System.out.println("[3 - Controller: On doc Deletion] " + "An error occurred while deleting the doctor.");
            Result exception = new Result(0, "An error occurred while deleting the doctor.");
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

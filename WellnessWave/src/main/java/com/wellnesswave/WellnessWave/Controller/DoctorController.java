package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Service.DoctorService;
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
        return doctorService.getAllDoctors();
    }

    @GetMapping("/getDoctorById")
    public Doctor getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PostMapping("/createDoctor")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor){
        try{
            doctorService.createDoctor(doctor);
            return ResponseEntity.ok("Doctor registered successfully");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Doctor registration failed");
        }
    }

    @PostMapping("/updateDoctor")
    public Doctor updateDoctor(@PathVariable Doctor doctor){
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/deleteDoctor")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }

    //check for other endpoints that we want to implement
}

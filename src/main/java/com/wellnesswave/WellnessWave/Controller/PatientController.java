package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getPatientById")
    public Patient getPatientById(@PathVariable Integer id){
        return patientService.getPatientById(id);
    }

    @PostMapping("/updatePatient")
    public Patient updatePatient(@PathVariable Patient patient){
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/deletePatient")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }
}

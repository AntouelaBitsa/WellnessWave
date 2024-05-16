package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Diagnosis;
import com.wellnesswave.WellnessWave.Service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnService;

    @GetMapping("/getAllDiagnosis")
    public List<Diagnosis> getAllDiagnosis(){
        return diagnService.getAllDiagnosis();
    }

    @GetMapping("/getDiagnById")
    public Diagnosis getDiagnById(@PathVariable Integer id){
        return diagnService.getDiagnById(id);
    }

    @PostMapping("/updateDiagn")
    public Diagnosis updateDiagn(@PathVariable Diagnosis diagn){
        return diagnService.updateDiagn(diagn);
    }

    @DeleteMapping("/deleteDiagn")
    public void deleteDiagn(@PathVariable Integer id){
        diagnService.deleteDiagn(id);
    }

    //check for other endpoints that we want to implement

}

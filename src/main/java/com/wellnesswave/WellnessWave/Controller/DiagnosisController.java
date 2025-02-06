package com.wellnesswave.WellnessWave.Controller;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Diagnosis;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Service.DiagnosisService;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/createDiagnosis")
    public ResponseEntity<Result> createDiagnosis(@RequestBody Diagnosis diagnosis){
        System.out.println(">[Dgn Cntrl]< Test Print : CONTROLLER ");
        System.out.println(diagnosis.toString());
        return new ResponseEntity<Result>(diagnService.createDiagnosis(diagnosis), HttpStatus.CREATED);
    }

    @GetMapping("/searchPatByAmka/{patAmka}")
    public ResponseEntity<Patient> searchPatByAmka(@PathVariable String patAmka){
        System.out.println(">[Dgn Cntrl 1: searchPatByAmka]< Test Print: CONTROLLER ");
        System.out.println(patAmka.toString());
        System.out.println(">[Dgn Cntrl 2: searchPatByAmka]< diagnService.getPatientByAmka: " + diagnService.getPatientByAmka(patAmka));
        return new ResponseEntity<>(diagnService.getPatientByAmka(patAmka), HttpStatus.CREATED);
    }

    @GetMapping("/getListOfDiagnosisByPatient/{patId}")
    public  ResponseEntity<List<Diagnosis>> getListOfDiagnosisByPatient(@PathVariable Integer patId){
        List<Diagnosis> diagnosisList = diagnService.diagnosisOfUser(patId);
        System.out.println("[1 Diagnosis Controller] getListOfDiagnosisByPatient: diagnosisList = " + diagnosisList);
        System.out.println("[2 Diagnosis Controller] getListOfDiagnosisByPatient: Call of service, before return statement");
        return new ResponseEntity<>(diagnosisList, HttpStatus.OK);
    }

//    @PostMapping("/updateDiagn")
//    public Diagnosis updateDiagn(@PathVariable Diagnosis diagn){
//        return diagnService.updateDiagn(diagn);
//    }

//    @DeleteMapping("/deleteDiagn")
//    public void deleteDiagn(@PathVariable Integer id){
//        diagnService.deleteDiagn(id);
//    }

    //check for other endpoints that we want to implement

}

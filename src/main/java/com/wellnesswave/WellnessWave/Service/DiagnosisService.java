package com.wellnesswave.WellnessWave.Service;

import com.google.gson.Gson;
import com.wellnesswave.WellnessWave.Entities.Appointments;
import com.wellnesswave.WellnessWave.Entities.Diagnosis;
import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.DiagnosisRepository;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import com.wellnesswave.WellnessWave.Repository.PatientRepository;
import com.wellnesswave.WellnessWave.Utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRep;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    //CRUD Operations Declaration
    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisRep.findAll();
    }

    public Diagnosis getDiagnById(Integer id){
        return diagnosisRep.findById(id).orElseThrow(()-> new RuntimeException("Diagnosis Not Found."));
    }

    public Result createDiagnosis(Diagnosis diagnosis){
        Doctor doc = doctorService.getDoctorById(diagnosis.getDoctor().getDocId());
        System.out.println(">[Dgn 1 Create]< docService : " + doc.toString());
        diagnosis.setDoctor(doc);
        Patient pat = patientService.getPatientById(diagnosis.getPatient().getPatientId());
        System.out.println(">[Dgn 2 Create]< patService : " + pat.toString());
        diagnosis.setPatient(pat);
        System.out.println(">[Dgn 3 Create]< Diagnosis class " + diagnosis.toString());
        if (diagnosis.equals(null)){
            return new Result(1, "One or more fields in diagnosis are empty");
        }

        try{
            diagnosisRep.save(diagnosis);
        }catch (Exception e){
            System.out.println(">[Dgn 5 Create]< Doctor " + diagnosis.getDoctor().toString());
            System.out.println(">[Dgn 6 Create]< Patient " + diagnosis.getPatient().toString());
            System.out.println(">[Dgn 4 Create]< Exception : " + e.getMessage());
            return new Result(1, "Oops an Exception occured during appointment booking");
        }
        return new Result(0, "Successful appointment booking");
    }

    //DONE: implementation of getPatientByAmka -> returned Patient
    public Patient getPatientByAmka(String patAmka){
        //Check if amka is passed as null
        if (patAmka == null){
            System.out.println(">[Dgn Service 1: getPatientByAmka]< " +
                    "Oops! There is no Patient with no such AMKA!");
            return null;
        }

        Patient p = patientRepository.findByPatAmka(patAmka);
        try {
            System.out.println("[Dgn Service 1: getPatientByAmka] -> p.toString(): " + p.toString());
        }catch (Exception e){
            System.out.println(">[Dgn Service 2: getPatientByAmka]< Exception: " + e);
        }
        return p;
    }

    public List<Diagnosis> diagnosisOfUser(Integer patId){
        //test
        System.out.println("[0 DiagnosisService] diagnosisOfUser: id of patient = " + patId);
        Patient patientCheck = patientService.getPatientById(patId);
        //check if id passed as null
        if (patId == null && patientCheck == null){
            System.out.println("[1 DiagnosisService] diagnosisOfUser: patientID == " + patId);
            System.out.println("[2 DiagnosisService] diagnosisOfUser: Incoming PatId daosen't exist or it is null! ");
            return Collections.emptyList();
        }

        List<Diagnosis> diagnosisList = new ArrayList<>();
        //Initialize Appointments List
        try {
            diagnosisList = diagnosisRep.findByPatient_PatientId(patId);
            System.out.println("SUCCESSFUL GET Request");
        }catch (Exception e){
            System.out.println("[2 DiagnosisService] diagnosisOfUser: Exception Occurred --> " + e);
        }
        return diagnosisList;
    }

//    public Diagnosis updateDiagn(Diagnosis diagn){
//        return diagnosisRep.save(diagn);
//    }

//    public void deleteDiagn(Integer id){
//        diagnosisRep.deleteById(id);
//    }
}

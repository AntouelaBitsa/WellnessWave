package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Diagnosis;
import com.wellnesswave.WellnessWave.Repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository diagnosisRep;

    //CRUD Operations Declaration
    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisRep.findAll();
    }

    public Diagnosis getDiagnById(Long id){
        return diagnosisRep.findById(id).orElse(null);
    }

    public Diagnosis updateDiagn(Diagnosis diagn){
        return diagnosisRep.save(diagn);
    }

    public void deleteDiagn(Long id){
        diagnosisRep.deleteById(id);
    }
}

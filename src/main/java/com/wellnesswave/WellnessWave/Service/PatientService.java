package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Patient;
import com.wellnesswave.WellnessWave.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRep;

    public List<Patient> getAllPatients(){
        return patientRep.findAll();
    }

    public Patient getPatientById(Long id){
        return patientRep.findById(id).orElse(null);
    }

    public Patient updatePatient(Patient patient){
        return patientRep.save(patient);
    }

    public void deletePatient(Long id){
        patientRep.deleteById(id);
    }
}

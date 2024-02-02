package com.wellnesswave.WellnessWave.Service;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRep;

    //CRUD Operations Declaration

    public List<Doctor> getAllDoctors(){
        return doctorRep.findAll();
    }

    public Doctor getDoctorById(Long id){
        return doctorRep.findById(id).orElse(null);
    }

    public Doctor createDoctor(Doctor doctor){
        if(!doctorRep.findDocByEmail(doctor.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return doctorRep.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor){
        return doctorRep.save(doctor);
    }

    public void deleteDoctor(Long id){
        doctorRep.deleteById(id);
    }

}

package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
    Doctor findByDocUsernameAndPassword(String docUsername, String password);

    ArrayList<Doctor> findByProfession(String spec);
//   boolean findDocByEmail(String email);
}

package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
    Doctor findByDocUsernameAndPassword(String docUsername, String password);
//   boolean findDocByEmail(String email);
}

package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends  JpaRepository<Doctor, Long>{
//    CrudRepository<Doctor, Long>
    boolean findDocByEmail(String email);
}

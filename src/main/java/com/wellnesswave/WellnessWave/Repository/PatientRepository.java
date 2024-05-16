package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}

package com.wellnesswave.WellnessWave.Repository;

import com.wellnesswave.WellnessWave.Entities.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {
    List<Appointments> findByPatient_PatientId(Integer patientId);
    List<Appointments> findByDoctor_DocId(Integer docId);
    Appointments findByAppointmentId(int appointmentId);
    List<Appointments> findByDateAndDoctor_DocId(LocalDate date, Integer userId);
    List<Appointments> findByDateAndPatient_PatientId(LocalDate dateFormatted, Integer userId);

}

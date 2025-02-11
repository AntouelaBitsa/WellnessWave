package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;
    private String appointInfo;

    //Foreign key - Parent
    @ManyToOne(optional = false)
    @JoinColumn(name = "doc_id", nullable = false)
    private Doctor doctor;

    //Foreign Key - Parent
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    private String status;

    public Appointments() {
    }

    public Appointments(LocalDate date, LocalTime time, String appointInfo, Doctor doctor, Patient patient, String status) {
        this.date = date;
        this.time = time;
        this.appointInfo = appointInfo;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
        System.out.println(">>> Appointment Controller doc address : " + doctor.getAddress());
    }

    public static List<Appointments> filterAndSortAppointments(List<Appointments> appointList){
        LocalDate today = LocalDate.now();

        // Filter appointments: Keep only those with date >= today
        List<Appointments> filteredAppointments = appointList.stream()
                .filter(appointments -> !appointments.getDate().isBefore(today))
                        .collect(Collectors.toList());

        // Sort by date first, then by time
        Collections.sort(filteredAppointments, Comparator
                .comparing(Appointments::getDate)
                .thenComparing(Appointments::getTime));

        // Print sorted appointments
        System.out.println("[Appointments Entity] Sorted Appointments List: " + filteredAppointments);
        return filteredAppointments;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAppointInfo() {
        return appointInfo;
    }

    public void setAppointInfo(String appointInfo) {
        this.appointInfo = appointInfo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "appointmentId=" + appointmentId +
                ", date=" + date +
                ", time=" + time +
                ", appointInfo='" + appointInfo + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", status=" + status +
                '}';
    }

    public boolean hasEmptyOrNull() {
        return date.equals(null) || time.equals(null) || appointInfo.isEmpty() ||appointInfo.equals(null);
    }
}

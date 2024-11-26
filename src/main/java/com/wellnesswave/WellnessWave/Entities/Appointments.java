package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    //Foreign key
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Doctor doctor;

    //Foreign Key
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String status;

    public Appointments() {
    }

    public Appointments(LocalDate date, LocalTime time, String appointInfo, Doctor doctor, Patient patient) {
        this.date = date;
        this.time = time;
        this.appointInfo = appointInfo;
        this.doctor = doctor;
        this.patient = patient;
        System.out.println(">>> Appointment Controller doc address : " + doctor.getAddress());
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
                '}';
    }

    public boolean hasEmptyOrNull() {
        return date.equals(null) || time.equals(null) || appointInfo.isEmpty() ||appointInfo.equals(null);
    }
}

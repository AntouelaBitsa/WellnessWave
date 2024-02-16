package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    //Foreign key
//    @OneToOne
    private Doctor docId;
    //Foreign key
//    @OneToOne
    private Patient patientId;
    private Date date;
    private Date time;
    private String appointInfo;

    public Appointments(int appointmentId, Doctor docId, Patient patId, Date date, Date time, String appointInfo) {
        this.appointmentId = appointmentId;
        this.docId = docId;
        this.patientId = patId;
        this.date = date;
        this.time = time;
        this.appointInfo = appointInfo;
    }

    public Appointments(Doctor docId, Patient patId, Date date, Date time, String appointInfo) {
        this.docId = docId;
        this.patientId = patId;
        this.date = date;
        this.time = time;
        this.appointInfo = appointInfo;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getDocId() {
        return docId;
    }

    public void setDocId(Doctor docId) {
        this.docId = docId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAppointInfo() {
        return appointInfo;
    }

    public void setAppointInfo(String appointInfo) {
        this.appointInfo = appointInfo;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "appointmentId=" + appointmentId +
                ", docId=" + docId +
                ", patId=" + patientId +
                ", date=" + date +
                ", time=" + time +
                ", appointInfo='" + appointInfo + '\'' +
                '}';
    }
}

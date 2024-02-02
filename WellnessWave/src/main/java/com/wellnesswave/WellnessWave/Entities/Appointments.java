package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    private int docId;
    private int patId;
    private Date date;
    private Date time;
    private String appointInfo;

    public Appointments(int appointmentId, int docId, int patId, Date date, Date time, String appointInfo) {
        this.appointmentId = appointmentId;
        this.docId = docId;
        this.patId = patId;
        this.date = date;
        this.time = time;
        this.appointInfo = appointInfo;
    }

    public Appointments(int docId, int patId, Date date, Date time, String appointInfo) {
        this.docId = docId;
        this.patId = patId;
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

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
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
                ", patId=" + patId +
                ", date=" + date +
                ", time=" + time +
                ", appointInfo='" + appointInfo + '\'' +
                '}';
    }
}

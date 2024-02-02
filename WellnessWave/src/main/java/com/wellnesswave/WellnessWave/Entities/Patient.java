package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String fisrtName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String dob; //date of birth
    private String patAmka;
    private String patPassword;

    public Patient(int patientId, String fisrtName, String lastName, String phoneNum, String email, String dob, String patAmka, String patPassword) {
        this.patientId = patientId;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.dob = dob;
        this.patAmka = patAmka;
        this.patPassword = patPassword;
    }

    public Patient(String fisrtName, String lastName, String phoneNum, String email, String dob, String patAmka, String patPassword) {
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.dob = dob;
        this.patAmka = patAmka;
        this.patPassword = patPassword;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPatAmka() {
        return patAmka;
    }

    public void setPatAmka(String patAmka) {
        this.patAmka = patAmka;
    }

    public String getPatPassword() {
        return patPassword;
    }

    public void setPatPassword(String patPassword) {
        this.patPassword = patPassword;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", fisrtName='" + fisrtName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", patAmka='" + patAmka + '\'' +
                ", patPassword='" + patPassword + '\'' +
                '}';
    }
}

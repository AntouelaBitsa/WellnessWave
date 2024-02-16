package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String dob; //date of birth
    private String patAmka;
    private String patPassword;
    //Foreign key
//    @OneToOne
    private int appId;
    //Foreign key
//    @OneToOne
    private int diagnId;
    private String patUsername;

    public Patient(int patientId, String fisrtName, String lastName, String phoneNum, String email, String dob, String patAmka, String patPassword) {
        this.patientId = patientId;
        this.firstName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.dob = dob;
        this.patAmka = patAmka;
        this.patPassword = patPassword;
    }

    public Patient(String fisrtName, String lastName, String phoneNum, String email, String dob, String patAmka, String patPassword) {
        this.firstName = fisrtName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getDiagnId() {
        return diagnId;
    }

    public void setDiagnId(int diagnId) {
        this.diagnId = diagnId;
    }

    public String getPatUsername() {
        return patUsername;
    }

    public void setPatUsername(String patUsername) {
        this.patUsername = patUsername;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", patAmka='" + patAmka + '\'' +
                ", patPassword='" + patPassword + '\'' +
                ", appId=" + appId +
                ", diagnId=" + diagnId +
                ", patUsername='" + patUsername + '\'' +
                '}';
    }
}

package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String dob; //date of birth
    @Column(nullable = false)
    private String patAmka;
    @Column(nullable = false)
    private String patPassword;
    @Column(nullable = false)
    private String patUsername;
    private int userType;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String phoneNum, String email, String dob, String patAmka, String patPassword, String patUsername, int userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.dob = dob;
        this.patAmka = patAmka;
        this.patPassword = patPassword;
        this.patUsername = patUsername;
        this.userType = userType;
    }

    public boolean hasEmptyOrNull(){
        return firstName.isEmpty() || firstName.equals(null) || lastName.isEmpty() || lastName.equals(null) || phoneNum.isEmpty() || phoneNum.equals(null) ||
                email.isEmpty() || email.equals(null) || dob.isEmpty() || dob.equals(null) || patAmka.isEmpty() || patAmka.equals(null) ||
                patPassword.isEmpty() || patPassword.equals(null) || patUsername.isEmpty() || patUsername.equals(null);
    }

    public Integer getPatientId() {
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
    public String getPatUsername() {
        return patUsername;
    }

    public void setPatUsername(String patUsername) {
        this.patUsername = patUsername;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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
                ", patUsername='" + patUsername + '\'' +
                '}';
    }
}

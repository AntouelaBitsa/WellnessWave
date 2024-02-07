package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String address;
    private String profession;
    private String amka;
    private String password;
    //Foreign key
    private int diagnId;
    //Foreign key
    private int appointmentId;
    private String docUsername;


    public Doctor(int docId, String fisrtName, String lastName, String phoneNum, String email, String address, String profession, String amka, String password) {
        this.docId = docId;
        this.firstName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.profession = profession;
        this.amka = amka;
        this.password = password;
    }

    public Doctor(String fisrtName, String lastName, String phoneNum, String email, String address, String profession, String amka, String password) {
        this.firstName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.profession = profession;
        this.amka = amka;
        this.password = password;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocUsername() {
        return docUsername;
    }

    public void setDocUsername(String docUsername) {
        this.docUsername = docUsername;
    }


    public int getDiagnId() {
        return diagnId;
    }

    public void setDiagnId(int diagnId) {
        this.diagnId = diagnId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "docId=" + docId +
                ", fisrtName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", amka='" + amka + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

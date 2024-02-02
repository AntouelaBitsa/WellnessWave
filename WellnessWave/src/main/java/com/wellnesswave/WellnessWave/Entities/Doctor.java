package com.wellnesswave.WellnessWave.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docId;
    private String fisrtName;
    private String lastName;
    private String phoneNum;
    private String email;
    private String address;
    private String profession;
    private String amka;
    private String password;
    private String docUsername;


    public Doctor(int docId, String fisrtName, String lastName, String phoneNum, String email, String address, String profession, String amka, String password) {
        this.docId = docId;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.profession = profession;
        this.amka = amka;
        this.password = password;
    }

    public Doctor(String fisrtName, String lastName, String phoneNum, String email, String address, String profession, String amka, String password) {
        this.fisrtName = fisrtName;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "docId=" + docId +
                ", fisrtName='" + fisrtName + '\'' +
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

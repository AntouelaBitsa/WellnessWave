package com.wellnesswave.WellnessWave.Utils;

import com.wellnesswave.WellnessWave.Entities.Doctor;

public class DoctorDTO {
    private Integer docId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String address;


    public DoctorDTO(Integer docId, String firstName, String lastName, String phoneNum, String address) {
        this.docId = docId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

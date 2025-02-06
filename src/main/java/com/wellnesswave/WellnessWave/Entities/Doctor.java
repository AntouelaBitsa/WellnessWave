package com.wellnesswave.WellnessWave.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNum;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String amka;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String profession;
    @Column(nullable = false)
    private String docUsername;
    private int userType;  //TODO: Change type to Integer
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "doc_id", referencedColumnName = "docId")
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Appointments> docAppointments = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "doc_id", referencedColumnName = "docId")
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Diagnosis> docDiagnosis = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BookedSlots> docBookedSlots = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String password, String phoneNum, String email, String amka, String address, String profession, String docUsername, int userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.amka = amka;
        this.address = address;
        this.profession = profession;
        this.docUsername = docUsername;
        this.userType = userType;
    }

    //Constructor for a list of specific doctors
//    public Doctor(Integer docId, String firstName, String lastName /*, String email*/) {
//        this.docId = docId;
//        this.firstName = firstName;
//        this.lastName = lastName;
////        this.email = email;
//    }

    public boolean hasEmptyOrNull(){
        //returns true if one or more fields are empty or null
//        if (firstName.isEmpty() || firstName.equals(null) || lastName.isEmpty() || lastName.equals(null) || password.isEmpty()
//                || password.equals(null) || phoneNum.isEmpty() || phoneNum.equals(null) || email.isEmpty() || email.equals(null)
//                || amka.isEmpty() || amka.equals(null) || address.isEmpty() || address.equals(null) || profession.isEmpty() || profession.equals(null)
//                || docUsername.isEmpty() || docUsername.equals(null)){
//            return true;
//        }
//        return false;
        return firstName.isEmpty() || firstName.equals(null) || lastName.isEmpty() || lastName.equals(null) || password.isEmpty()
                || password.equals(null) || phoneNum.isEmpty() || phoneNum.equals(null) || email.isEmpty() || email.equals(null)
                || amka.isEmpty() || amka.equals(null) || address.isEmpty() || address.equals(null) || profession.isEmpty() || profession.equals(null)
                || docUsername.isEmpty() || docUsername.equals(null);
    }


    //thodoris method style
    public boolean hasEmptyOrNull2(){
        boolean flag = false;
        flag = flag || firstName.isEmpty() || firstName.equals(null);
        //copy and  paste for all the other fields
        return flag;
    }



    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocId() {
        return docId;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

//    @Override
//    public String toString() {
//        return "Doctor{" +
//                "docId=" + docId +
//                ", fisrtName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", phoneNum='" + phoneNum + '\'' +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                ", profession='" + profession + '\'' +
//                ", amka='" + amka + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id=" + docId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String createJSONList() {
        String jsonSession = "[\" {\" " +
                "\"docId\": \"" + docId + "\"," +
                "\"docFirstName\": \"" + firstName + "\"," +
                "\"docLastName\": \"" + lastName + "\"," +
                "}\" ]\"";
//        "[\n  {\n    \"docId\": 1,\n    \"firstName\": \"Antouela\",\n    \"lastName\": \"Bitsa\"\n  },\n  {\n    \"docId\": 6,\n    \"firstName\": \"Matheos\",\n    \"lastName\": \"Vasiliadis\"\n  }\n]"
        return jsonSession;
    }
}

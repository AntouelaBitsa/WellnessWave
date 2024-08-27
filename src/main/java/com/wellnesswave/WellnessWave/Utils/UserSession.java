package com.wellnesswave.WellnessWave.Utils;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Entities.Patient;

public class UserSession {
    //AUTHENTICATION contains basic and most importatnt info of an entity
    private Integer patientId;
    private Integer doctorId;
    private String firstName;
    private String lastName;
    private String username;
//    private String password;
    private String email;
    private String phoneNum;
    private String address;
    private String profession;
    private String patDob;
//    private String dob;
    private int userType;

    public UserSession() {

    }

    public UserSession(Doctor d) {
        try {
            this.doctorId = d.getDocId();
            this.firstName = d.getFirstName();
            this.lastName = d.getLastName();
            this.username = d.getDocUsername();
            this.email = d.getEmail();
            this.phoneNum = d.getPhoneNum();
            this.address = d.getAddress();
            this.profession = d.getProfession();
            this.userType = d.getUserType(); //code for doctor = 1
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    //DONE: CONSTRUCTOR FOR PATIENT
    public UserSession(Patient p) {
        try {
            this.patientId = p.getPatientId();
            this.firstName = p.getFirstName();
            this.lastName = p.getLastName();
            this.username = p.getPatUsername();
            this.email = p.getEmail();
            this.phoneNum = p.getPhoneNum();
            this.patDob = p.getDob();
            this.userType = p.getUserType(); //code for patient = 2
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    public boolean tryLogIn() {
        System.out.println("fName: " + firstName + " lName: " + lastName );
        if(firstName.equals(null)){
            return  false;
        }
        return true;
//        if(!(username.equals(this.username) && password.equals(this.password))){
//            return false;
//        }
//        return true;
    }

    public String getDocSessionJSON() {
        String jsonSession = "{" +
                "\"docId\": \"" + doctorId + "\"," +
                "\"docFirstName\": \"" + firstName + "\"," +
                "\"docLastName\": \"" + lastName + "\"," +
                "\"docUsername\": \"" + username + "\"," +
                "\"docEmail\": \"" + email + "\"," +
                "\"docPhoneNum\": \"" + phoneNum + "\"," +
                "\"docProfession\": \"" + profession + "\"," +
                "\"docAddress\": \"" + address + "\"," +
                "\"userType\": \"" + userType + "\"" +
                "}";
        return jsonSession;
    }

    public String getPatSessionJSON() {
        String jsonSession = "{" +
                "\"patientId\": \"" + patientId + "\"," +
                "\"firstName\": \"" + firstName + "\"," +
                "\"lastName\": \"" + lastName + "\"," +
                "\"patUsername\": \"" + username + "\"," +
                "\"email\": \"" + email + "\"," +
                "\"phoneNum\": \"" + phoneNum + "\"," +
                "\"dob\": \"" + patDob + "\"," +
                "\"userType\": \"" + userType + "\"" +
                "}";
        return jsonSession;
    }

    public UserSession getAllFields(){
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getProfession() {
        return profession;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getPatDob() {
        return patDob;
    }
}

package com.wellnesswave.WellnessWave.Utils;

import com.wellnesswave.WellnessWave.Entities.Doctor;
import com.wellnesswave.WellnessWave.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSession {
    //AUTHENTICATION contains basic and most importatnt info of an entity
    private String firstName;
    private String lastName;
    private String docUsername;
    private String password;
    private String email;
    private String phoneNum;
    private String address;
    private String profession;

    @Autowired
    private DoctorRepository doctorRepository;

    public UserSession(String firstName, String lastName, String username, String password, String email, String phoneNum, String address, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.docUsername = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.profession = profession;
    }

    public UserSession() {

    }

    public UserSession(String username, String password) {
        this.docUsername = username;
        this.password = password;
    }

    public boolean tryLogIn() {
//        It works also like this but only if we can add parameters to the method
//        if(!(userName.equals(docUsername) && pass.equals(password))){
//            return false;
//        }
//        return true;

        if(!(docUsername.equals(this.docUsername) && password.equals(this.password))){
            return false;
        }
        return true;
    }

    public String getSessionJSON() {
        String jsonSession = "first_name: "+ firstName + " ";
        jsonSession += "last_name: "+ lastName + " ";
        jsonSession += "username: "+ docUsername + " ";
        jsonSession += "password: "+ password + " ";
        jsonSession += "phoneNum: "+ phoneNum + " ";
        jsonSession += "address: "+ address + " ";
        jsonSession += "profession: "+ profession + " ";
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

    public String getDocUsername() {
        return docUsername;
    }

    public String getPassword() {
        return password;
    }

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
}

package com.wellnesswave.WellnessWave.Utils;

import com.wellnesswave.WellnessWave.Entities.Doctor;

import java.util.List;

public class Result {
    private int status;
    private String message;
//    private List<Doctor> specialisedDoc;

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
        System.out.println("1st Constructor");
//        System.out.println("PRINT MESSAGE : " + message);
    }

//    public Result(int status, List<Doctor> docList) {
//        //THIS WAS A SECOND CONSTRUCTOR
//        this.status = status;
//        this.specialisedDoc = docList;
//        System.out.println("2nd Constructor For Specialised Doctor");
//    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public String getUserSession() {
//        return userSession;
//    }
//
//    public void setUserSession(String userSession) {
//        this.userSession = userSession;
//    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }


//    public List<Doctor> getSpecialisedDoc() {
//        return specialisedDoc;
//    }
//
//    @Override
//    public String toString() {
//        return "Result{" +
//                "status=" + status +
//                ", specilisedDoc=" + specialisedDoc +
//                '}';
//    }
}

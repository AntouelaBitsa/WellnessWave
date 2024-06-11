package com.wellnesswave.WellnessWave.Utils;

public class Result {
    private int status;
    private String message;
    private UserSession userSession;

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
        System.out.println("1st Constructor");
        System.out.println("PRINT MESSAGE : " + message);
    }

    public Result(int status, UserSession userSession) {
        //THIS WAS A SECOND CONSTRUCTOR
//        Result r = new Result(); //TEST
        this.status = status;
        this.userSession = userSession;
        System.out.println("2nd Constructor USER-SESSION");
//        r.setStatus(this.status); //TEST
//        r.setUserSession(this.userSession);  //TEST
//        System.out.println("r object afte CHANGES" + r.toString());  //TEST
//        return r;  //TEST
    }

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
}

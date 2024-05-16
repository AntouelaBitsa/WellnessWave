package com.wellnesswave.WellnessWave.Utils;

public class Result {
    private int status;
    private String message;
    private UserSession userSession;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, UserSession userSession) {
        this.status = status;
        this.userSession = userSession;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

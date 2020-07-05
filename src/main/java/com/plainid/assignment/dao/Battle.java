package com.plainid.assignment.dao;

/**
 * Created by Omer Dekel on 04/07/2020.
 */
public class Battle {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    private String message;
    private StatusType status;

}

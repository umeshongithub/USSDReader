package com.times.ussd.dto;

/**
 * Created by umesh on 24/11/15.
 */
public class Ussd {
    private long id;
    private String message;
    private long timeStamp;
    private String balance;

    public Ussd() {

    }

    public Ussd(String message, long timeStamp, String balance) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
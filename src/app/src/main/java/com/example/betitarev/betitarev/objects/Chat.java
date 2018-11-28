package com.example.betitarev.betitarev.objects;

import java.util.Date;

public class Chat {

    private User sender, receiver;
    private Date date;
    private String message;

    public Chat(User sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = new Date();
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}

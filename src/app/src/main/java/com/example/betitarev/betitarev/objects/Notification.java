package com.example.betitarev.betitarev.objects;

public class Notification {

    private String title, message;
    private String senderMail, receiverMail;

    public Notification(String title, String message, String senderMail, String receiverMail) {
        this.title = title;
        this.message = message;
        this.senderMail = senderMail;
        this.receiverMail = receiverMail;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderMail() {
        return senderMail;
    }

    public String getReceiverMail() {
        return receiverMail;
    }
}

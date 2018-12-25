package com.example.betitarev.betitarev.objects;

public class Notification {

    private String title, message;
    private String senderToken, receiverToken;

    public Notification(String title, String message, String senderToken, String receiverToken) {
        this.title = title;
        this.message = message;
        this.senderToken = senderToken;
        this.receiverToken = receiverToken;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderToken() {
        return senderToken;
    }

    public String getReceiverToken() {
        return receiverToken;
    }
}

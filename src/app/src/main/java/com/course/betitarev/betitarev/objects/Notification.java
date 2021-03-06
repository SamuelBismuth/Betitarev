package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Notification.
 */
public class Notification {

    private String title, message;
    private String senderToken, receiverToken;
    private String betId;

    /**
     * Constructor.
     *
     * @param title
     * @param message
     * @param senderToken
     * @param receiverToken
     * @param betId
     */
    public Notification(String title, String message, String senderToken, String receiverToken, String betId) {
        this.title = title;
        this.message = message;
        this.senderToken = senderToken;
        this.receiverToken = receiverToken;
        this.betId = betId;
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

    public String getBetId() {
        return betId;
    }
}

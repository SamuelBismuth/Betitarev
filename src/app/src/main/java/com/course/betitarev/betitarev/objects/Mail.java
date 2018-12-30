package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Mail.
 */
public class Mail {

    private String mail;

    private Mail() {
    }

    /**
     * Constructor.
     *
     * @param mail
     */
    public Mail(String mail) {
        this.mail = mail;
    }

    protected boolean isValidateMail() {
        return false;
    }

    private boolean includeAt() {
        return false;
    }

    private boolean includeDot() {
        return false;
    }

    public String getMail() {
        return mail;
    }

}

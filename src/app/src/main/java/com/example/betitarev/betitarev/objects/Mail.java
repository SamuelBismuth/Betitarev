package com.example.betitarev.betitarev.objects;

public class Mail {

    private String mail;

    private Mail(){}

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

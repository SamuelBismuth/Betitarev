package com.example.betitarev.betitarev.objects;

public class Friend {

    private Mail mail;
    private String completeName;

    public Friend(Mail mail) {
        this.mail = mail;
    }

    public Friend(Mail mail, String completeName) {
        this.mail = mail;
        this.completeName = completeName;
    }

    public String getCompleteName() {
        return completeName;
    }

    public Mail getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return this.getCompleteName();
    }
}

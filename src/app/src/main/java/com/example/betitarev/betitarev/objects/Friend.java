package com.example.betitarev.betitarev.objects;

import android.support.annotation.NonNull;

public class Friend implements Comparable<Friend> {

    private Mail mail;
    private String completeName;

    /**
     * TODO: Check this with Jonato.
     */
    public Friend(){
        this.mail = new Mail(" ");
        this.completeName = " ";
    }

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
        return this.getMail().getMail() +" " + this.getCompleteName();
    }

    @Override
    public int compareTo(@NonNull Friend friend) {
        return this.mail.getMail().compareTo(friend.getMail().getMail());
    }
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Friend) && (toString().equals(obj.toString()));
    }
    }

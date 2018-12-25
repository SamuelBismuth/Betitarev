package com.example.betitarev.betitarev.objects;

import android.support.annotation.NonNull;

public class Friend  implements Comparable<Friend> {
    private String fullName, pushToken;
    private Mail mail;

    public Friend(){}

    public Friend(String fullName, Mail mail, String token){
        this.fullName = fullName;
        this.mail = mail;
        this.pushToken = token;
    }

    @Override
    public String toString() {
        return this.getMail().getMail() + " " + this.getFullName();
    }

    @Override
    public int compareTo(@NonNull Friend friend) {
        return this.getMail().getMail().compareTo(friend.getMail().getMail());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Friend) && (toString().equals(obj.toString()));
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}

package com.example.betitarev.betitarev.objects;

import android.net.Uri;

public abstract class User {

    private String name, familyName;  // path to the picture in our server.
    private Uri picture;
    private Mail mail;
    private String pushToken;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String familyName, Mail mail, String pushToken) {
        this.name = name;
        this.familyName = familyName;
        this.mail = mail;
        this.pushToken = pushToken;
    }

    public User(String name, String familyName, Uri picture, Mail mail, String pushToken) {
        this.name = name;
        this.familyName = familyName;
        this.picture = picture;
        this.mail = mail;
        this.pushToken = pushToken;
    }

    public abstract void updateDatabase();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Uri getPicture() {
        return picture;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getCompleteName() {
        return this.getName() + " " + this.getFamilyName();
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}

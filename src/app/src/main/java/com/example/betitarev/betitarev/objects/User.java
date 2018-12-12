package com.example.betitarev.betitarev.objects;

import com.google.firebase.database.IgnoreExtraProperties;

public abstract class User {

    private String name, familyName, picture;  // path to the picture in our server.
    private Mail mail;
    private Friends friends;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String familyName, Mail mail) {
        this.name = name;
        this.familyName = familyName;
        this.mail = mail;
        this.friends = null;
        this.picture = "../../../Betitarev/pictures/anonymous.png";
    }

    public User(String name, String familyName, String picture, Password password, Mail mail) {
        this.name = name;
        this.familyName = familyName;
        this.picture = picture;
        this.mail = mail;
        this.friends = null;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

}

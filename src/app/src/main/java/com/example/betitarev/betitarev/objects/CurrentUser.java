package com.example.betitarev.betitarev.objects;

import android.net.Uri;

// Singleton
public class CurrentUser {

    private String name;
    private String familyName;



    private String userid;  // path to the picture in our server.
    private Uri picture;
    private Mail mail;
    private Statistics statistics;
    private Friends friends;

    static CurrentUser instance = null; // Singleton implementation.

    static public CurrentUser getInstance(Player user) {
        if(instance != null)
            return instance;
        instance = new CurrentUser(user);
        return instance;
    }

    static public CurrentUser getInstance(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends) {
        if(instance != null)
            return instance;
        instance = new CurrentUser(name, familyName, picture, mail, statistics, friends);
        return instance;
    }

    private CurrentUser(Player user){
        this.name = user.getName();
        this.familyName = user.getFamilyName();
        this.picture = user.getPicture();
        this.mail = user.getMail();
        this.statistics = user.getStatistics();
        this.friends = user.getFriends();
    }
    private CurrentUser(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends) {
        this.name = name;
        this.familyName = familyName;
        this.picture = picture;
        this.mail = mail;
        this.statistics = statistics;
        this.friends = friends;
    }

    public static CurrentUser getInstance() {
        return instance;
    }

    public void setPicture(Uri picture) {
        this.picture = picture;

    }




    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Uri getPicture() {
        return picture;
    }

    public Mail getMail() {
        return mail;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Friends getFriends() {
        return friends;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}

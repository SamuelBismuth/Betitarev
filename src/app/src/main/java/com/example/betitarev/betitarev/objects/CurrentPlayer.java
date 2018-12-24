package com.example.betitarev.betitarev.objects;

import android.net.Uri;

// Singleton
public class CurrentPlayer extends Player {

    private String userid; // path to the picture in our server.

    static CurrentPlayer instance = null; // Singleton implementation.

    static public CurrentPlayer getInstance(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends, String pushToken) {
        if(instance != null)
            return instance;
        instance = new CurrentPlayer(name, familyName, picture, mail, statistics, friends, pushToken);
        return instance;
    }

    private CurrentPlayer(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends, String pushToken) {
        super(name, familyName, picture, mail, statistics, friends, pushToken);
    }

    public static CurrentPlayer getInstance() {
        return instance;
    }

    @Override
    public void updateDatabase() {

    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }


}

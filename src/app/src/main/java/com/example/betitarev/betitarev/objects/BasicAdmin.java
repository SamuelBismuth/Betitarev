package com.example.betitarev.betitarev.objects;

import android.net.Uri;

public class BasicAdmin extends User implements Admin {


    public BasicAdmin(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
    }

    public BasicAdmin(String name, String familyName, Uri picture, Mail mail, String pushToken) {
        super(name, familyName, picture, mail, pushToken);
    }

    @Override
    public void updateDatabase() {
    }

    @Override
    public boolean sendWarning(User player) {
        return false;
    }

    @Override
    public boolean removePlayer(User player) {
        return false;
    }

}
package com.example.betitarev.betitarev.objects;

public class BasicAdmin extends User implements Admin {


    public BasicAdmin(String name, String familyName, Mail mail, String userId) {
        super(name, familyName, mail);
    }

    public BasicAdmin(String name, String familyName, String picture, Mail mail, String userId) {
        super(name, familyName, picture, mail);
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
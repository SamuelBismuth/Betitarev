package com.example.betitarev.betitarev.objects;

public class BasicAdmin extends User implements Admin {


    public BasicAdmin(String name, String familyName, Password password, Mail mail) {
        super(name, familyName, password, mail);
    }

    public BasicAdmin(String name, String familyName, String picture, Password password, Mail mail) {
        super(name, familyName, picture, password, mail);
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
package com.example.betitarev.betitarev.objects;

//Singleton
public class SuperAdmin extends User implements Admin {

    public SuperAdmin(String name, String familyName, Mail mail) {
        super(name, familyName, mail);
    }

    public SuperAdmin(String name, String familyName, String picture, Mail mail) {
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

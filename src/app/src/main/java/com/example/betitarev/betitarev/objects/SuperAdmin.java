package com.example.betitarev.betitarev.objects;

//Singleton
public class SuperAdmin extends User implements Admin {

    public SuperAdmin(String name, String familyName, Password password, Mail mail) {
        super(name, familyName, password, mail);
    }

    public SuperAdmin(String name, String familyName, String picture, Password password, Mail mail) {
        super(name, familyName, picture, password, mail);
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

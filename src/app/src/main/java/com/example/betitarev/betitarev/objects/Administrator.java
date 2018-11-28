package com.example.betitarev.betitarev.objects;

public class Administrator extends User {


    public Administrator(String name, String familyName, Password password, Mail mail) {
        super(name, familyName, password, mail);
    }

    public Administrator(String name, String familyName, String picture, Password password, Mail mail) {
        super(name, familyName, picture, password, mail);
    }
}
package com.example.betitarev.betitarev.objects;

public class Player extends User {

    private Statistics statistics;

    public Player(String name, String familyName, Password password, Mail mail) {
        super(name, familyName, password, mail);
        this.statistics = statistics.initialize();
    }

    public Player(String name, String familyName, String picture, Password password, Mail mail) {
        super(name, familyName, picture, password, mail);
        this.statistics = statistics.initialize();

    }
}

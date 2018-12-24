package com.example.betitarev.betitarev.objects;

public class Player extends User {



    public Player(){
        super();
    }
    public Player(String name, String familyName, Mail mail) {
        super(name, familyName, mail);

    }

    public Player(String name, String familyName, String picture, Mail mail) {
        super(name, familyName, picture, mail);

    }

    public Player(String name, String familyName, String picture, Mail mail, Statistics statistics, Friends friends) {
        super(name, familyName, picture, mail, statistics, friends);

    }

    @Override
    public void updateDatabase() {
    }


}

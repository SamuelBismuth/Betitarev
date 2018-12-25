package com.example.betitarev.betitarev.objects;

public class Player extends User {



    public Player(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
    }

    public Player(String name, String familyName, String picture, Mail mail, String pushToken) {
        super(name, familyName, picture, mail, pushToken);
    }

    public Player(String name, String familyName, String picture, Mail mail, Statistics statistics, Friends friends, String pushToken) {
        super(name, familyName, picture, mail, statistics, friends, pushToken);
    }

    public Player(){
        super();
    }

    public Player(Player player, String userid){
        super(player, userid);
    }

    @Override
    public void updateDatabase() {
    }


}

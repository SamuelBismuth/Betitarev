package com.example.betitarev.betitarev.objects;

public class Player extends User {

    public Player(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
    }

    public Player(Player player, String userid) {
        super(player, userid);
    }


}

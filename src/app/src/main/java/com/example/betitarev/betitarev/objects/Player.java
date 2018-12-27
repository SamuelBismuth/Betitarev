package com.example.betitarev.betitarev.objects;

public class Player extends User {

    public Player() {
    }

    public Player(Player player) {
        super(player);
    }

    public Player(String name, String familyName, Mail email, String userId, String pushToken) {
        super(name, familyName, email, userId, pushToken);
    }
    public Player(User user){
        super(user);
    }
}

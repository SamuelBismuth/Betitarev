package com.example.betitarev.betitarev.objects;

// Singleton
public class CurrentPlayer extends Player {
    static CurrentPlayer instance = null; // Singleton implementation.

    static public CurrentPlayer getInstance(Player player, String userid) {
        if(instance != null)
            return instance;
        instance = new CurrentPlayer(player, userid);
        return instance;
    }

    private CurrentPlayer(String name, String familyName, String picture, Mail mail, Statistics statistics, Friends friends, String pushToken) {
        super(name, familyName, picture, mail, statistics, friends, pushToken);
    }
    private CurrentPlayer(Player player, String userid){
        super(player, userid);
    }

    public static CurrentPlayer getInstance() {
        return instance;
    }

    @Override
    public void updateDatabase() {

    }

}

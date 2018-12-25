package com.example.betitarev.betitarev.objects;

// Singleton
public class CurrentPlayer extends Player {

    static CurrentPlayer instance = null; // Singleton implementation.

    static public CurrentPlayer getInstance(Player player, String userId) {
        if(instance != null)
            return instance;
        instance = new CurrentPlayer(player, userId);
        return instance;
    }

    private CurrentPlayer(Player player, String userId) {
        super(player, userId);
    }

    public static CurrentPlayer getInstance() {
        return instance;
    }





}

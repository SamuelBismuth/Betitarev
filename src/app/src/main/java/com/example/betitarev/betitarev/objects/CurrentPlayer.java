package com.example.betitarev.betitarev.objects;

// Singleton
public class CurrentPlayer extends Player {


    static CurrentPlayer instance = null; // Singleton implementation.

    private CurrentPlayer(Player player) {
        super(player);
    }

    static public CurrentPlayer getInstance(Player player) {
        if (instance != null)
            return instance;
        instance = new CurrentPlayer(player);
        return instance;
    }

    public static CurrentPlayer getInstance() {
        return instance;
    }

    public static void signOut() {
        instance = null;
    }


}

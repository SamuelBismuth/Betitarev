package com.course.betitarev.betitarev.objects;

// Singleton


/**
 * This class represents the object Current player
 */
public class CurrentPlayer extends Player {

    static CurrentPlayer instance = null; // Singleton implementation.

    /**
     * Constructor.
     * @param player
     */
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

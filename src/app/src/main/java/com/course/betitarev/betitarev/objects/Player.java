package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Player.
 */
public class Player extends User {

    public Player() {
    }

    /**
     * Constructor.
     *
     * @param player
     */
    public Player(Player player) {
        super(player);
    }

    /**
     * Constructor.
     *
     * @param name
     * @param familyName
     * @param email
     * @param userId
     * @param pushToken
     */
    public Player(String name, String familyName, Mail email, String userId, String pushToken) {
        super(name, familyName, email, userId, pushToken);
    }

    public Player(User user) {
        super(user);
    }
}

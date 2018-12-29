package com.example.betitarev.betitarev.objects;

/**
 * This class represents the object Arbitrator which judge the game.
 */
public class Arbitrator implements BetRole {

    private User user;  // The user which is the arbitrator.

    public Arbitrator() {
    }

    public Arbitrator(User user) {
        this.user = user;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }

    public User getUser() {
        return user;
    }
}

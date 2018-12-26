package com.example.betitarev.betitarev.objects;

public class Arbitrator implements BetRole {

    private User user;

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

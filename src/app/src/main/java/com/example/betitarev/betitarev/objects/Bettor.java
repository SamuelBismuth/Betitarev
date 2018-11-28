package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private User user;

    public Bettor(User user) {
        this.user = user;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }
}

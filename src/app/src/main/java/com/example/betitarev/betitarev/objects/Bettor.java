package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private Player user;
    private String guessing;

    public Bettor() {
    }

    public Bettor(Player user, String guessing) {
        this.user = user;
        this.guessing = guessing;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }

    public Player getUser() {
        return user;
    }

    public String getGuessing() {
        return guessing;
    }
}

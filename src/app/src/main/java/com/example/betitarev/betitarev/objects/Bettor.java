package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private Player user;
    private BettorStatus bettorStatus;
    private String guessing;

    public Bettor() {}

    public Bettor(Player user, BettorStatus bettorStatus, String guessing) {
        this.user = user;
        this.bettorStatus = bettorStatus;
        this.guessing = guessing;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }

    public Player getUser() {
        return user;
    }

    public BettorStatus getBettorStatus() {
        return bettorStatus;
    }

    public String getGuessing() {
        return guessing;
    }
}

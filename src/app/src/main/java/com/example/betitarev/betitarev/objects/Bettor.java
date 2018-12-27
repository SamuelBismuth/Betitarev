package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private Player user;
    private BettorStatus bettorStatus;
    private String value;

    public Bettor() {}

    public Bettor(Player user, BettorStatus bettorStatus, String value) {
        this.user = user;
        this.bettorStatus = bettorStatus;
        this.value = value;
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

    public String getValue() {
        return value;
    }
}

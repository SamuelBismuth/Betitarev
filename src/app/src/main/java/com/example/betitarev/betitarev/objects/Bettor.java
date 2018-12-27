package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private Player user;
    private BettorStatus bettorStatus;
    public Bettor(){}

    public Bettor(Player user, BettorStatus bettorStatus) {
        this.user = user;
        this.bettorStatus = bettorStatus;
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
}

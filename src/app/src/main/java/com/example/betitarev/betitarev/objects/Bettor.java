package com.example.betitarev.betitarev.objects;

public class Bettor implements BetRole {

    private User user;
    private BettorStatus bettorStatus;

    public Bettor(User user, BettorStatus bettorStatus) {
        this.user = user;
        this.bettorStatus = bettorStatus;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }

    public User getUser() {
        return user;
    }

    public BettorStatus getBettorStatus() {
        return bettorStatus;
    }
}

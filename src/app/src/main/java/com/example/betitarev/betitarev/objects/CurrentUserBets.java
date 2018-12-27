package com.example.betitarev.betitarev.objects;

import java.util.List;

public class CurrentUserBets {

    private static CurrentUserBets instance = null; // Singleton implementation.
    private List<Bet> bets;

    private CurrentUserBets(List<Bet> Bets) {
        this.bets = Bets;
    }

    public static CurrentUserBets getInstance(List<Bet> bets) {
        if (instance == null)
            instance = new CurrentUserBets(bets);
        return instance;
    }

    public static  CurrentUserBets getInstance() {
        return instance;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        bets.add(bet);
    }

}

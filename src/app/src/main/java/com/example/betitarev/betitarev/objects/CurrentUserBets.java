package com.example.betitarev.betitarev.objects;

import java.util.List;

public class CurrentUserBets {
    private static CurrentUserBets instance = null; // Singleton implementation.
    private List<Bet> Bets;

    static public CurrentUserBets getInstance(List<Bet> Bets) {
        if(instance != null)
            return instance;
        instance = new CurrentUserBets(Bets);
        return instance;
    }

    private CurrentUserBets(List<Bet> Bets) {
        this.Bets = Bets;
    }

    public static CurrentUserBets getInstance() {
        return instance;
    }
}

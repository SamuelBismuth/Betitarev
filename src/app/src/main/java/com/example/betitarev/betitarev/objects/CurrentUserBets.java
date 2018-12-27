package com.example.betitarev.betitarev.objects;

import java.util.List;

public class CurrentUserBets  {
    private static CurrentUserBets instance = null; // Singleton implementation.
    private List<Bet> bets;

    private CurrentUserBets(List<Bet> Bets) {
        this.bets = Bets;
    }
    public List<Bet> getBets(){
        return bets;
    }
    public static CurrentUserBets getInstance() {
        return instance;
    }
    public void addBet(Bet bet){
        bets.add(bet);

    }

}

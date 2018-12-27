package com.example.betitarev.betitarev.objects;

public class BetWithoutArbitrator extends Bet {

    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        super(player1, player2, phrase, fictiveMoney, new Arbitrator(player1.getUser()));
    }

}

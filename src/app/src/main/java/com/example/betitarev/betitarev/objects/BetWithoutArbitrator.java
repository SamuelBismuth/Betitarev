package com.example.betitarev.betitarev.objects;

public class BetWithoutArbitrator extends Bet {

    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney, BetStatus status) {
        super(player1, player2, phrase, fictiveMoney, status);
    }

}

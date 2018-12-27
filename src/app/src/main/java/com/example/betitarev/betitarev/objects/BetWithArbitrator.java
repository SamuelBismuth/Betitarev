package com.example.betitarev.betitarev.objects;

public class BetWithArbitrator extends Bet {

    private Arbitrator arbitrator;

    public BetWithArbitrator(Bettor player1, Bettor player2, String phrase,  FictiveMoney fictiveMoney, Arbitrator arbitrator, BetStatus status) {
        super(player1, player2, phrase, fictiveMoney, status);
        this.arbitrator = arbitrator;
    }

    public Arbitrator getArbitrator() {
        return arbitrator;
    }




}

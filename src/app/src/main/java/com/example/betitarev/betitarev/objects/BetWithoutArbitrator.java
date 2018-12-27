package com.example.betitarev.betitarev.objects;

public class BetWithoutArbitrator extends Bet {

    Arbitrator arbitrator;

    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        super(player1, player2, phrase, fictiveMoney,  new Arbitrator(player1.getUser()));
        this.arbitrator = new Arbitrator(player1.getUser());
    }

    @Override
    public Arbitrator getArbitrator() {
        return arbitrator;
    }
}

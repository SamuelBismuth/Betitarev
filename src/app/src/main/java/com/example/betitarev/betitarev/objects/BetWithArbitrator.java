package com.example.betitarev.betitarev.objects;

public class BetWithArbitrator extends Bet {

    private Arbitrator arbitrator;

    public BetWithArbitrator(Bettor player1, Bettor player2, String phrase,  FictiveMoney fictiveMoney, Arbitrator arbitrator) {
        super(player1, player2, phrase, fictiveMoney);
        this.arbitrator = arbitrator;
    }

    public BetWithArbitrator(Bettor player1, Bettor player2, FictiveMoney fictiveMoney, Arbitrator arbitrator) {
        super(player1, player2, fictiveMoney);
        this.arbitrator = arbitrator;
    }

    @Override
    protected Bettor appointWinner() {
        return null;
    }

    public Arbitrator getArbitrator() {
        return arbitrator;
    }
}

package com.example.betitarev.betitarev.objects;

public class BetWithoutArbitrator extends Bet {


    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase) {
        super(player1, player2, phrase);
    }

    public BetWithoutArbitrator(Bettor player1, Bettor player2, FictiveMoney fictiveMoney) {
        super(player1, player2, fictiveMoney);
    }

    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        super(player1, player2, phrase, fictiveMoney);
    }

    @Override
    protected Bettor appointWinner() {
        return null;
    }
}

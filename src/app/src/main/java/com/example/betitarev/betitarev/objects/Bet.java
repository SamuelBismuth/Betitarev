package com.example.betitarev.betitarev.objects;

public abstract class Bet {

    private Bettor player1, player2;
    private String phrase;
    private FictiveMoney fictiveMoney;
    private BetStatus status;
    private Arbitrator arbitrator;

    public Bet(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        this.player1 = player1;
        this.player2 = player2;
        this.fictiveMoney = fictiveMoney;
        this.phrase = phrase;
        this.status = BetStatus.OnProcess;
    }
    public Bet(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney, Arbitrator arbitrator) {
        this.player1 = player1;
        this.player2 = player2;
        this.fictiveMoney = fictiveMoney;
        this.phrase = phrase;
        this.status = BetStatus.OnProcess;
        this.arbitrator = arbitrator;
    }


    protected abstract Bettor appointWinner();  // Can be draw.

    public Bettor getPlayer1() {
        return player1;
    }

    public Bettor getPlayer2() {
        return player2;
    }

    public String getPhrase() {
        return phrase;
    }

    public Arbitrator getArbitrator() {
        return arbitrator;
    }

    public FictiveMoney getFictiveMoney() {
        return fictiveMoney;
    }

    public BetStatus getStatus() {
        return status;
    }
}

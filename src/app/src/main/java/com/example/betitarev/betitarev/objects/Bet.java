package com.example.betitarev.betitarev.objects;

public abstract class Bet {

    private Bettor player1, player2;
    private String phrase;
    private FictiveMoney fictiveMoney;

    public Bet(Bettor player1, Bettor player2, String phrase) {
        this.player1 = player1;
        this.player2 = player2;
        this.phrase = phrase;
        this.fictiveMoney = null;
    }

    public Bet(Bettor player1, Bettor player2, FictiveMoney fictiveMoney) {
        this.player1 = player1;
        this.player2 = player2;
        this.fictiveMoney = fictiveMoney;
        this.phrase = null;
    }
    public Bet(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        this.player1 = player1;
        this.player2 = player2;
        this.fictiveMoney = fictiveMoney;
        this.phrase = phrase;
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

    public FictiveMoney getFictiveMoney() {
        return fictiveMoney;
    }
}

package com.course.betitarev.betitarev.objects;

/**
 * This class represents the object Bet.
 */
public class Bet {

    private Bettor player1, player2;
    private String phrase;
    private FictiveMoney fictiveMoney;
    private Arbitrator arbitrator;
    private String winner;


    public Bet() {}

    /**
     * Constructor.
     * @param player1
     * @param player2
     * @param phrase
     * @param fictiveMoney
     * @param arbitrator
     */
    public Bet(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney, Arbitrator arbitrator) {
        this.player1 = player1;
        this.player2 = player2;
        this.fictiveMoney = fictiveMoney;
        this.phrase = phrase;
        this.arbitrator = arbitrator;
        this.winner = " ";
    }

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

    public String getWinner() {
        return winner;
    }
}

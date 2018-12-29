package com.example.betitarev.betitarev.objects;


/**
 * This class represents the object Bet without arbitrator.
 */
public class BetWithoutArbitrator extends Bet {

    Arbitrator arbitrator;

    /**
     * Constructor.
     * @param player1
     * @param player2
     * @param phrase
     * @param fictiveMoney
     */
    public BetWithoutArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney) {
        super(player1, player2, phrase, fictiveMoney,  new Arbitrator(player1.getUser()));
        this.arbitrator = new Arbitrator(player1.getUser());
    }

    @Override
    public Arbitrator getArbitrator() {
        return arbitrator;
    }
}

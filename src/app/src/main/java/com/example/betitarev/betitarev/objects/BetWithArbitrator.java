package com.example.betitarev.betitarev.objects;


/**
 * This class represents the object Bet With Arbitrator.
 */
public class BetWithArbitrator extends Bet {

    private Arbitrator arbitrator;

    /**
     * Constructor.
     * @param player1
     * @param player2
     * @param phrase
     * @param fictiveMoney
     * @param arbitrator
     */
    public BetWithArbitrator(Bettor player1, Bettor player2, String phrase, FictiveMoney fictiveMoney, Arbitrator arbitrator) {
        super(player1, player2, phrase, fictiveMoney, arbitrator);
        this.arbitrator = arbitrator;
    }


    @Override
    public Arbitrator getArbitrator() {
        return arbitrator;
    }


}



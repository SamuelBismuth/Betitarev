package com.course.betitarev.betitarev.objects;


/**
 * This class represents the object Bettor.
 */
public class Bettor implements BetRole {

    private Player user;
    private String guessing;

    public Bettor() {
    }

    /**
     * Constructor.
     * @param user
     * @param guessing
     */
    public Bettor(Player user, String guessing) {
        this.user = user;
        this.guessing = guessing;
    }

    @Override
    public boolean acceptQuery(Bet bet) {
        return false;
    }

    public Player getUser() {
        return user;
    }

    public String getGuessing() {
        return guessing;
    }

    public void setGuessing(String guessing) {
        this.guessing = guessing;
    }
}

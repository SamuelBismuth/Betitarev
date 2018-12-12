package com.example.betitarev.betitarev.objects;

public class Player extends User {

    private Statistics statistics;

    public Player(String name, String familyName, Mail mail, String userId) {
        super(name, familyName, mail, userId);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, String picture, Mail mail, String userId) {
        super(name, familyName, picture, mail, userId);
        this.statistics = new Statistics();

    }

    @Override
    public void updateDatabase() {
    }

    public Statistics getStatistics() {
        return statistics;
    }

}

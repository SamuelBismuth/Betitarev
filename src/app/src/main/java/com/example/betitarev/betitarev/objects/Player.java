package com.example.betitarev.betitarev.objects;

public class Player extends User {

    private Statistics statistics;

    public Player(String name, String familyName, Mail mail) {
        super(name, familyName, mail);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, String picture, Password password, Mail mail) {
        super(name, familyName, picture, password, mail);
        this.statistics = new Statistics();

    }

    @Override
    public void updateDatabase() {
    }

    public Statistics getStatistics() {
        return statistics;
    }

}

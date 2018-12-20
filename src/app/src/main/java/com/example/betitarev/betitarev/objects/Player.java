package com.example.betitarev.betitarev.objects;

import android.net.Uri;

public class Player extends User {

    private Statistics statistics;
    private Friends friends;

    public Player(String name, String familyName, Mail mail) {
        super(name, familyName, mail);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, Uri picture, Mail mail) {
        super(name, familyName, picture, mail);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends) {
        super(name, familyName, picture, mail);
        this.statistics = statistics;
        this.friends = friends;
    }

    @Override
    public void updateDatabase() {
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Friends getFriends() {
        return friends;
    }

}

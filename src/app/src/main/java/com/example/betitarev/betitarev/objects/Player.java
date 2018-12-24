package com.example.betitarev.betitarev.objects;

import android.net.Uri;

public class Player extends User {

    private Statistics statistics;
    private Friends friends;

    public Player(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, Uri picture, Mail mail, String pushToken) {
        super(name, familyName, picture, mail, pushToken);
        this.statistics = new Statistics();
    }

    public Player(String name, String familyName, Uri picture, Mail mail, Statistics statistics, Friends friends, String pushToken) {
        super(name, familyName, picture, mail, pushToken);
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

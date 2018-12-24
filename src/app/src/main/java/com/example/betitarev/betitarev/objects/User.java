package com.example.betitarev.betitarev.objects;

public abstract class User {

    private String name, familyName;  // path to the picture in our server.
    private String picture;
    private Mail mail;
    private Statistics statistics;
    private Friends friends;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String familyName, Mail mail) {
        this.name = name;
        this.familyName = familyName;
        this.mail = mail;
        this.picture = " ";
        this.statistics = new Statistics();
        this.friends = new Friends();
    }

    public User(String name, String familyName, String picture, Mail mail) {
        this.name = name;
        this.familyName = familyName;
        this.picture = picture;
        this.mail = mail;
        this.statistics = new Statistics();
        this.friends = new Friends();
    }

    public User(String name, String familyName, String picture, Mail mail, Statistics statistics, Friends friends) {
        this.name = name;
        this.familyName = familyName;
        this.picture = picture;
        this.mail = mail;
        this.friends = friends;
        this.statistics = statistics;
    }


    public abstract void updateDatabase();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getCompleteName() {
        return this.getName() + " " + this.getFamilyName();
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", picture=" + picture +
                ", mail=" + mail +
                ", statistics=" + statistics +
                ", friends=" + friends +
                '}';
    }
}

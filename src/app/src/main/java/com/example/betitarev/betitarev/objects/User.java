package com.example.betitarev.betitarev.objects;

public class User {

    private String name, familyName;
    private String picture;
    private Mail mail;
    private String userid;
    private String pushToken;
    private Statistics statistics;
    private Friends friends;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String familyName, Mail mail, String userId, String pushToken) {
        this.name = name;
        this.familyName = familyName;
        this.mail = mail;
        this.picture = " ";
        this.statistics = new Statistics();
        this.friends = new Friends();
        this.pushToken = pushToken;
        this.userid = userId;
    }

    public User(User user, String userid) {
        this.name = user.getName();
        this.familyName = user.getFamilyName();
        this.picture = user.getPicture();
        this.mail = user.getMail();
        this.pushToken = user.getPushToken();
        this.friends = user.getFriends();
        this.statistics = user.getStatistics();
        this.userid = userid;

    }

    public User(User user) {
        this.name = user.getName();
        this.familyName = user.getFamilyName();
        this.picture = user.getPicture();
        this.mail = user.getMail();
        this.pushToken = user.getPushToken();
        this.friends = user.getFriends();
        this.statistics = user.getStatistics();
        this.userid = user.getUserid();

    }

    public User(String name, String familyName, Mail mail, String pushToken) {
        this.name = name;
        this.familyName = familyName;
        this.mail = mail;
        this.picture = " ";
        this.statistics = new Statistics();
        this.friends = new Friends();
        this.pushToken = pushToken;
    }

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

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
                ", userid =" + userid +
                '}';

    }
}

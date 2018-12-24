package com.example.betitarev.betitarev.objects;

// Singleton
public class CurrentUser {

    private String name;
    private String familyName;



    private String userid;  // path to the picture in our server.
    private String picture;
    private Mail mail;
    private Statistics statistics;
    private Friends friends;

    static CurrentUser instance = null; // Singleton implementation.

    static public CurrentUser getInstance(User user, String userid) {
        if(instance != null)
            return instance;
        instance = new CurrentUser(user, userid);
        return instance;
    }



    private CurrentUser(User user, String userid){
        this.userid = userid;
        this.name = user.getName();
        this.familyName = user.getFamilyName();
        this.picture = user.getPicture();
        this.mail = user.getMail();
        this.statistics = user.getStatistics();
        this.friends = user.getFriends();
    }


    public static CurrentUser getInstance() {
        return instance;
    }

    public void setPicture(String picture) {
        this.picture = picture;

    }




    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPicture() {
        return picture;
    }

    public Mail getMail() {
        return mail;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Friends getFriends() {
        return friends;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}

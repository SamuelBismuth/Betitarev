package com.example.betitarev.betitarev.objects;

import java.util.Date;

public class Friend {

    private User user;
    private Date date;
    private Discussion discussion;

    public Friend(User user) {
        this.user = user;
        this.date = new Date();  // Should be initialized as the current date.
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Discussion getDiscussion() {
        return discussion;
    }
}

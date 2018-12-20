package com.example.betitarev.betitarev.objects;

import java.util.Set;

public class Friends {

    private Set<Friend> friends;

    public Friends(Set<Friend> friends) {
        this.friends = friends;
    }

    protected int getNumOfFriends() {
        return 0;
    }

    protected boolean isFriend(Mail mail) {
        return false;
    }

    protected boolean addFriend(Mail mail) {
        return false;
    }

    protected boolean removeFriend(Mail mail) {
        return false;
    }

    public Set<Friend> getFriends() {
        return friends;
    }
}

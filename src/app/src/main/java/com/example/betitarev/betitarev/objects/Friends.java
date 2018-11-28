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

    protected boolean isFriend(User user) {
        return false;
    }

    protected boolean addFriend(User user) {
        return false;
    }

    protected boolean removeFriend(User user) {
        return false;
    }

    public Set<Friend> getFriends() {
        return friends;
    }
}

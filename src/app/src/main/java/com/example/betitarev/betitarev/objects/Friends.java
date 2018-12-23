package com.example.betitarev.betitarev.objects;

import android.util.Log;

import java.util.Set;

public class Friends {

    private Set<Friend> friends;

    public Friends(Set<Friend> friends) {
        this.friends = friends;
    }

    protected int getNumOfFriends() {
        return friends.size();
    }

    protected boolean isFriend(Friend friend) {
        if (friends.contains(friend))
            return true;
        return false;
    }

    protected boolean addFriend(Friend friend) {
        try {
            friends.add(friend);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    protected boolean removeFriend(Friend friend) {
        try{
            friends.remove(friend);
            return true;
        }
        catch(Exception e) {
            Log.e("error", e.getMessage());
            return false;
        }
    }

    public Set<Friend> getFriends() {
        return friends;
    }
}

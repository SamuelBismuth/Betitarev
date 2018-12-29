package com.example.betitarev.betitarev.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents the object Friends.
 */
public class Friends {

    private List<Friend> friends;

    public Friends() {
        this.friends = new ArrayList<>();
    }

    protected int getNumOfFriends() {
        return friends.size();
    }

    /**
     * Constructor.
     *
     * @param friend
     * @return
     */
    public boolean isFriend(Friend friend) {
        if (friends.contains(friend))
            return true;
        return false;
    }

    public boolean addFriend(Friend friend) {
        try {
            friends.add(friend);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeFriend(Friend friend) {
        try {
            friends.remove(friend);
            return true;
        } catch (Exception e) {
            Log.e("error", e.getMessage());
            return false;
        }
    }

    public List<Friend> getFriends() {
        return friends;
    }
}

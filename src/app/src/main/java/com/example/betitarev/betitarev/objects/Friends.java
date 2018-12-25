package com.example.betitarev.betitarev.objects;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Friends {

    private List<Friend> friends;

    public Friends(){
        this.friends = new ArrayList<>();
        this.friends.add(new Friend());
    }

    public Friends(Friends friends){
        this.friends = friends.friends;
    }

    public Friends(List<Friend> friends) {
        this.friends = friends;
    }

    protected int getNumOfFriends() {
        return friends.size();
    }

    public boolean isFriend(Friend friend) {
        if (friends.contains(friend))
            return true;
        return false;
    }

    public boolean addFriend(Friend friend) {
        try {
            friends.add(friend);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean removeFriend(Friend friend) {
        try{
            friends.remove(friend);
            return true;
        }
        catch(Exception e) {
            Log.e("error", e.getMessage());
            return false;
        }
    }

    public List<Friend> getFriends() {
        return friends;
    }
}

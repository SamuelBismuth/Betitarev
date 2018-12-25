package com.example.betitarev.betitarev.objects;

import android.support.annotation.NonNull;

public class Friend extends User implements Comparable<Friend> {

    public Friend(){}

    public Friend(User user){
        super(user);
    }

    @Override
    public String toString() {
        return this.getMail().getMail() + " " + this.getCompleteName();
    }

    @Override
    public int compareTo(@NonNull Friend friend) {
        return this.getMail().getMail().compareTo(friend.getMail().getMail());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Friend) && (toString().equals(obj.toString()));
    }
}

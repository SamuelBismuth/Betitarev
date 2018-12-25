package com.example.betitarev.betitarev.objects;

import android.support.annotation.NonNull;

public class Friend extends User implements Comparable<Friend> {

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

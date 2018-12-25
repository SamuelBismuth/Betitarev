package com.example.betitarev.betitarev.objects;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BasicAdmin extends User implements Admin {

    public BasicAdmin(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
    }

    @Override
    public boolean sendWarning(User player) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("notifcations");
        String notifId = mFirebaseDatabase.push().getKey();
        String message = "";
        String senderToken = "";
        String receiverToken = "";
        Notification notif = new Notification("Bet request!!", message, senderToken, receiverToken);
        mFirebaseDatabase.child(notifId).setValue(notif);

        return false;
    }

    @Override
    public boolean removePlayer(User player) {


        return false;
    }

}
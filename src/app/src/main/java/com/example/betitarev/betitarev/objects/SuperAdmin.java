package com.example.betitarev.betitarev.objects;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Singleton
public class SuperAdmin extends User implements Admin {

    public SuperAdmin(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
    }

    @Override
    public boolean sendWarning(User player) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        String notifId = mFirebaseDatabase.push().getKey();
        String message = "this is warning before expel.\n please avoid of cheating and\\or unappropriate bets";
        Notification notif = new Notification("warning before expel:", message, CurrentAdmin.getInstance().getPushToken(), player.getPushToken());
        mFirebaseDatabase.child(notifId).setValue(notif);
        return false;
    }

    @Override
    public boolean removePlayer(User player) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        mFirebaseDatabase.child(player.getPushToken()).removeValue();
        return false;
    }

}

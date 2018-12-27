package com.example.betitarev.betitarev.objects;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.betitarev.betitarev.helper.FireBaseQuery.removeUser;

public class BasicAdmin extends User implements Admin {
    static List<String> AdminPushToken;
    public BasicAdmin(String name, String familyName, Mail mail, String pushToken) {
        super(name, familyName, mail, pushToken);
        if(AdminPushToken==null)
            AdminPushToken= new ArrayList<String>();
        AdminPushToken.add(pushToken);
    }
    public BasicAdmin(BasicAdmin ba, String userid){super(ba,userid);}
    @Override
    public boolean sendWarning(User player) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference();
        String notifId = mFirebaseDatabase.push().getKey();
        String message = "this is warning before expel.\n please avoid of cheating and\\or unappropriate bets";
        Notification notif = new Notification("warning before expel:", message, CurrentAdmin.getInstance().getPushToken(), player.getPushToken(), "");
        mFirebaseDatabase.child(notifId).setValue(notif);
        return false;
    }

    @Override
    public boolean removePlayer(User user) {
        Friend the_user= new Friend(user.getName() + " " + user.getFamilyName(), user.getMail(), user.getPushToken());
        // remove relations between this admin to the user
        if(CurrentPlayer.getInstance().getFriends().isFriend(the_user))
            CurrentPlayer.getInstance().getFriends().removeFriend(the_user);

        for(Friend friend : user.getFriends().getFriends())
        {
                User friendOfTheUser = UsersNamesHashmap.getAllKeysForValue(friend.getFullName()).get(0);
                Log.e("hhhhhhh", "one success");
                friendOfTheUser.getFriends().removeFriend(the_user);
        }

        removeUser(user.getUserid());
        //        DatabaseReference mFirebaseDatabase;
//        FirebaseDatabase mFirebaseInstance;
//        mFirebaseInstance = FirebaseDatabase.getInstance();
//        mFirebaseDatabase = mFirebaseInstance.getReference();
//        if(!AdminPushToken.contains(pushToken))
//            mFirebaseDatabase.child(pushToken).removeValue();


//      FirebaseDatabase.getInstance().;
//        node.setValue(null);

     //   DatabaseReference node = FirebaseDatabase.getInstance().getReference("users");

        return false;
    }

}
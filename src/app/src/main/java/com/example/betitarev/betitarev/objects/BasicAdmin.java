package com.example.betitarev.betitarev.objects;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.betitarev.betitarev.libraries.FireBaseQuery.removeUser;

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
        Notification notif = new Notification("warning before expel:", message, CurrentAdmin.getInstance().getPushToken(), player.getPushToken());
        mFirebaseDatabase.child(notifId).setValue(notif);
        return false;
    }

    @Override
    public boolean removePlayer(User user) {
        Friend this_user= new Friend(user.getName() + " " + user.getFamilyName(), user.getMail(), user.getPushToken());
        for(Friend friend : user.getFriends().getFriends())
        {
            if(!friend.getFullName().equals(CurrentAdmin.getInstance().getName()+" "+CurrentAdmin.getInstance().getFamilyName())) {
                User friendOfFriend = UsersNamesHashmap.getAllKeysForValue(friend.getFullName()).get(0);
                Log.e("hhhhhhh", "one success");
                friendOfFriend.getFriends().removeFriend(this_user);
            }
            else{
                CurrentAdmin.getInstance().getFriends().removeFriend(this_user);
            }
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
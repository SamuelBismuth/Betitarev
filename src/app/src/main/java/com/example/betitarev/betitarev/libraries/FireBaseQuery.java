package com.example.betitarev.betitarev.libraries;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.objects.CurrentPlayer;
import com.example.betitarev.betitarev.objects.Friend;
import com.example.betitarev.betitarev.objects.Friends;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.Statistic;
import com.example.betitarev.betitarev.objects.Statistics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.LinkedHashSet;
import java.util.Set;

public class FireBaseQuery {

    private static StorageReference storageRef, pathReference;
    private static FirebaseStorage storage;
    private static String name, familyName;
    private static String pushToken;
    private static Statistics statistics;
    private static Friends friends;
    private static Uri picture;
    private static FirebaseAuth auth;

    private static Set<Friend> friendSet = new LinkedHashSet<>();
    private static Set<DataSnapshot> mailSet = new LinkedHashSet<>();

    public static Mail getCurrentMail() {
        auth = FirebaseAuth.getInstance();
        return new Mail(auth.getCurrentUser().getEmail());
    }

    /**
     * Add the statistics and the friends on the database.
     *
     * @param email
     * @param mainActivity
     */
    public static void loadCurrentUser(final Mail email, final MainActivity mainActivity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    name = datas.child("name").getValue().toString();
                    familyName = datas.child("familyName").getValue().toString();
                    statistics = new Statistics(new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat/counter").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat/counter").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat/counter").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat/counter").getValue().toString())));
                    pushToken = datas.child("pushToken").getValue().toString();
                    for (DataSnapshot friend_local : datas.child("friends").getChildren())
                        mailSet.add(friend_local);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                    reference.orderByChild("mail/mail").addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot friend : mailSet)
                                for (DataSnapshot user : dataSnapshot.getChildren())
                                    if (user.child("mail/mail").getValue().toString().equals(friend.getValue().toString()))
                                        friendSet.add(new Friend(new Mail(friend.getValue().toString()),
                                                user.child("name").getValue().toString() +
                                                        " " + user.child("familyName").getValue().toString()));
                            friends = new Friends(friendSet);
                            storage = FirebaseStorage.getInstance();
                            storageRef = storage.getReference();
                            pathReference = storageRef.child("images/" + email.getMail() + "/profile");
                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    picture = uri;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.e("downloadImage", "failed");
                                }
                            });
                            CurrentPlayer.getInstance(name, familyName, picture, email, statistics, friends, pushToken);
                            mainActivity.begin();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}


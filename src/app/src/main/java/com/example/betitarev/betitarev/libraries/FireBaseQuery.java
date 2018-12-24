package com.example.betitarev.betitarev.libraries;

import android.app.Activity;
import android.content.Context;
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
import com.example.betitarev.betitarev.objects.UsersNamesHashmap;
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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FireBaseQuery {

    private static StorageReference storageRef, pathReference;
    private static FirebaseStorage storage;
    private static String name, familyName, name1, familyName1;

    private static String pushToken;
    private static Statistics statistics;
    private static Friends friends;
    private static Uri picture;
    private static FirebaseAuth auth;
    private static List<Friend> friendSet = new ArrayList<>();
    private static Set<DataSnapshot> mailSet = new LinkedHashSet<>();
    private static Set<Mail> allEmailsSet = new LinkedHashSet<>();

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
        reference.orderByChild("mail/mail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Mail currentMail = new Mail(datas.child("mail/mail").getValue().toString());
                    allEmailsSet.add(currentMail);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

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

                    friends = datas.child("friends").getValue(Friends.class);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                    reference.orderByChild("mail/mail").addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            friends = new Friends(friendSet);
                            storage = FirebaseStorage.getInstance();
                            storageRef = storage.getReference();
                            pathReference = storageRef.child("images/" + email.getMail() + "/profile");
                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    picture = uri;
                                    if(CurrentPlayer.getInstance()!=null)
                                    CurrentPlayer.getInstance().setPicture(uri);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.e("downloadImage", "failed");
                                }
                            });
                            CurrentPlayer.getInstance(name, familyName, picture, email, statistics, friends, pushToken);
                            UsersNamesHashmap.getInstance(allEmailsSet);
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
    public static void loadUserNameHashMap(final Mail mail, final UsersNamesHashmap USH){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("mail/mail").equalTo(mail.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    name1 = datas.child("name").getValue().toString();
                    familyName1 = datas.child("familyName").getValue().toString();
                    USH.getHashmap().put(mail, name1+" "+ familyName1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public static void updateUserFriends(final Context con) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("mail/mail").equalTo(CurrentPlayer.getInstance().getMail().getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()){
                    CurrentPlayer.getInstance().setUserid(datas.getKey());
                }
                reference.child(CurrentPlayer.getInstance().getUserid()).child("friends").setValue(CurrentPlayer.getInstance().getFriends());
                Activity a = (Activity) con;
                a.finish();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}


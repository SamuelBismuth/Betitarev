package com.example.betitarev.betitarev.libraries;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.objects.CurrentUser;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.Player;
import com.example.betitarev.betitarev.objects.User;
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

import java.util.LinkedHashSet;
import java.util.Set;

public class FireBaseQuery {


    private static String userid, name1, familyName1;

    private static User user;
    private static FirebaseAuth auth;
    private static Set<Mail> allEmailsSet = new LinkedHashSet<>();
    private static FirebaseStorage storage;
    private static StorageReference storageReference;


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
                    user = datas.getValue(Player.class);
                    userid = datas.getKey();
                }
                CurrentUser.getInstance(user, userid);
                Log.e("userDetails", user.toString());
                UsersNamesHashmap.getInstance(allEmailsSet);
                mainActivity.begin();

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
        reference.orderByChild("mail/mail").equalTo(CurrentUser.getInstance().getMail().getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reference.child(CurrentUser.getInstance().getUserid()).child("friends").setValue(CurrentUser.getInstance().getFriends());
                Activity a = (Activity) con;
                a.finish();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public static void updateUserPictureUri() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/" +CurrentUser.getInstance().getMail().getMail()+"/profile");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users/");
                final Uri Furi = uri;
                CurrentUser.getInstance().setPicture(Furi.toString());
                reference.child(CurrentUser.getInstance().getUserid()).child("picture").setValue(Furi.toString());
                Log.e("update pictrue Uri","5");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("downloadImage", "failed");
            }
        });
    }
}


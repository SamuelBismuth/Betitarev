package com.example.betitarev.betitarev.libraries;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.objects.CurrentUser;
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

public class FireBaseQuery {

    private static StorageReference storageRef, pathReference;
    private static FirebaseStorage storage;
    private static String name, familyName;
    private static Statistics statistics;
    private static Uri picture;
    private static FirebaseAuth auth;


    public static Mail getCurrentMail() {
        auth = FirebaseAuth.getInstance();
        return new Mail(auth.getCurrentUser().getEmail());
    }

    /**
     * Add the statistics and the friends on the database.
     *
     * @param email
     */
    public static void loadCurrentUser(final Mail email) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    name = datas.child("name").getValue().toString();
                    familyName = datas.child("familyName").getValue().toString();
                    statistics = new Statistics(new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat").getValue().toString())),
                            new Statistic(Integer.parseInt(datas.child("statistics/arbitratorStat").getValue().toString())));
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
                    CurrentUser.getInstance(name, familyName, picture, email, statistics, null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

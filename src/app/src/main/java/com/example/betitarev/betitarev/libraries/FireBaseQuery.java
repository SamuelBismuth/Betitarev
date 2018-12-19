package com.example.betitarev.betitarev.libraries;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.Player;
import com.example.betitarev.betitarev.objects.User;
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

import static java.security.AccessController.getContext;

public class FireBaseQuery {

    private static StorageReference storageRef, pathReference;
    private static FirebaseStorage storage;
    private static String name, familyName;
    private static Uri picture;


    public static User getPlayer(final Mail email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    name = datas.child("name").getValue().toString();
                    familyName = datas.child("familyName").getValue().toString();
                    storage = FirebaseStorage.getInstance();
                    storageRef = storage.getReference();
                    pathReference = storageRef.child("images/"+ email.getMail() +"/profile");
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
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return new Player(name ,familyName, picture, email);
    }
}

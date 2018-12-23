package com.example.betitarev.betitarev.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.objects.CurrentUser;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.UsersNamesHashmap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static java.security.AccessController.getContext;

public class AnotherProfileActivity extends AppCompatActivity {
    private static FirebaseAuth auth;
    private static String Name;
    private static Mail Email;
    private TextView mNameTextView, mEmailTextView;
    private ImageView mPictureSrc;
    private Button mAddFriendBtn;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference pathReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Name= null;
            } else {
                Name= extras.getString("Name");
            }
        } else {
            Name= (String) savedInstanceState.getSerializable("Name");
        }
        Email = UsersNamesHashmap.getAllKeysForValue(Name).get(0);

        mNameTextView = findViewById(R.id.name);
        mNameTextView.setText(Name);

        mEmailTextView = findViewById(R.id.email);
        mEmailTextView.setText(Email.getMail());

        mPictureSrc =  findViewById(R.id.profile_image);
        setProfileImage();

        mAddFriendBtn = (Button) findViewById(R.id.btn_add_friend);
        mAddFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setProfileImage() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        pathReference = storageRef.child("images/" + Email.getMail() + "/profile");
        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(mPictureSrc);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("downloadImage", "failed");
            }
        });

    }

}

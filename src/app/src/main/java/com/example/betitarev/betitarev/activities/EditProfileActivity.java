package com.example.betitarev.betitarev.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.activities.registration.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class EditProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText inputFirstName, inputLastName, inputOldPassword, inputNewPassword;
    private ImageView mImageView;
    private Button btnSaveChanges;
    private FirebaseAuth auth;
    private String Email;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private boolean changedProfileImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputFirstName = (EditText) findViewById(R.id.editFirstName);
        inputLastName = (EditText) findViewById(R.id.editLastName);
        inputOldPassword = (EditText) findViewById(R.id.editOldPassword);
        inputNewPassword = (EditText) findViewById(R.id.editNewPassword);
        btnSaveChanges = (Button) findViewById(R.id.btn_save_changes);
        auth = FirebaseAuth.getInstance();
        Email = auth.getCurrentUser().getEmail();
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference = reference.orderByChild("mail/mail").equalTo(Email).getRef();

        ImageView editProfileImage = (ImageView) findViewById(R.id.edit_profile_image) ;
        editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });

        updateEditTextNameFromDb();

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = inputFirstName.getText().toString();
                final String lastName = inputLastName.getText().toString();
                updateNameData(firstName, lastName);
                if(changedProfileImage) {
                    uploadImage();
                }

                finish();



            }
        });

    }

    private void uploadImage() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/" +Email+"/profile");
        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache();
        Bitmap bitmap = mImageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        ref.putBytes(data);
    }

    private void updateEditTextNameFromDb() {
        reference.orderByChild("mail/mail").equalTo(Email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    inputFirstName.setText(datas.child("name").getValue().toString());
                    inputLastName.setText(datas.child("familyName").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateNameData(final String firstName, final String lastName) {
        reference.orderByChild("mail/mail").equalTo(Email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    datas.child("name").getRef().setValue(firstName);
                    datas.child("familyName").getRef().setValue(lastName);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView = (ImageView) findViewById(R.id.edit_profile_image);
            mImageView.setImageBitmap(imageBitmap);

            changedProfileImage = true;
        }
    }

}

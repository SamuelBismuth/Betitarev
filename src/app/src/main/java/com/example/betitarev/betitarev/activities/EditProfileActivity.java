package com.example.betitarev.betitarev.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.activities.registration.LoginActivity;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.User;
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

import static com.example.betitarev.betitarev.libraries.FireBaseQuery.getCurrentMail;
import static com.example.betitarev.betitarev.libraries.FireBaseQuery.getPlayer;

public class EditProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText inputFirstName, inputLastName, inputOldPassword, inputNewPassword;
    private ImageView mImageView;
    private Button btnSaveChanges;
    private FirebaseAuth auth;
    private Mail Email;
    private User user;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private boolean changedProfileImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputFirstName =  findViewById(R.id.editFirstName);
        inputLastName =  findViewById(R.id.editLastName);
        inputOldPassword = findViewById(R.id.editOldPassword);
        inputNewPassword =  findViewById(R.id.editNewPassword);
        btnSaveChanges =  findViewById(R.id.btn_save_changes);

        Email = getCurrentMail();
        user = getPlayer(Email);
        inputFirstName.setText(user.getName());
        inputLastName.setText(user.getFamilyName());


        ImageView editProfileImage = (ImageView) findViewById(R.id.edit_profile_image) ;
        editProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });


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
        bitmap.compress(Bitmap.CompressFormat.PNG, 200, baos);
        byte[] data = baos.toByteArray();
        ref.putBytes(data);
    }



    private void updateNameData(final String firstName, final String lastName) {
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.orderByChild("mail/mail").equalTo(Email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
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

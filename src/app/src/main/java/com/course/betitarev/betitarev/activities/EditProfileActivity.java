package com.course.betitarev.betitarev.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.course.betitarev.betitarev.R;
import com.course.betitarev.betitarev.helper.FireBaseQuery;
import com.course.betitarev.betitarev.objects.CurrentPlayer;
import com.course.betitarev.betitarev.objects.Mail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import static com.course.betitarev.betitarev.helper.FireBaseQuery.getCurrentMail;

/**
 * This is the activity the edit the profile.
 * By editing the profile the meaning is change the picture, the name, and the password.
 */
public class EditProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText inputFirstName, inputLastName, inputOldPassword, inputNewPassword;  // The EditText for the activity.
    private ImageView mImageView;
    private FirebaseAuth auth;
    private Mail email;
    private FirebaseUser FBUser;
    private boolean changedProfileImage = false;

    /**
     * The function on create is call every time we create this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputFirstName = findViewById(R.id.editFirstName);
        inputLastName = findViewById(R.id.editLastName);
        inputOldPassword = findViewById(R.id.editOldPassword);
        inputNewPassword = findViewById(R.id.editNewPassword);
        Button btnSaveChanges = findViewById(R.id.btn_save_changes);
        auth = FirebaseAuth.getInstance();
        email = getCurrentMail();
        CurrentPlayer user = CurrentPlayer.getInstance();
        inputFirstName.setText(user.getName());
        inputLastName.setText(user.getFamilyName());


        ImageView editProfileImage = findViewById(R.id.edit_profile_image);
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
                if (changedProfileImage) {
                    uploadImage();
                }
                if (inputOldPassword.getText().toString().length() >= 1) {
                    updatePassword(inputOldPassword.getText().toString(), inputNewPassword.getText().toString());
                }
                finish();
            }
        });
    }

    /**
     * This method allows {@link com.course.betitarev.betitarev.objects.User} to edit the password.
     * @param oldPass
     * @param newPass
     */
    private void updatePassword(String oldPass, final String newPass) {
        FBUser = auth.getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email.getMail(), oldPass);
        FBUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FBUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password Successfully Modified", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * This method upload the image from our database.
     */
    private void uploadImage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/" + email.getMail() + "/profile");
        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache();
        Bitmap bitmap = mImageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        ref.putBytes(data);
        Log.e("trying to upload image", "success");
        FireBaseQuery.updateUserPictureUri();

    }

    /**
     * This method allows the {@link com.course.betitarev.betitarev.objects.User} to update his name.
     * @param firstName
     * @param lastName
     */
    private void updateNameData(final String firstName, final String lastName) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    datas.child("name").getRef().setValue(firstName);
                    datas.child("familyName").getRef().setValue(lastName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        CurrentPlayer.getInstance().setName(firstName);
        CurrentPlayer.getInstance().setFamilyName(lastName);

    }

    /**
     * This funciton dispatch the picture.
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * As a result of the activity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView = findViewById(R.id.edit_profile_image);
            mImageView.setImageBitmap(imageBitmap);

            changedProfileImage = true;
        }
    }

}

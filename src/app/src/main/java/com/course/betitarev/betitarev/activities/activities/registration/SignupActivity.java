package com.course.betitarev.betitarev.activities.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.course.betitarev.betitarev.R;
import com.course.betitarev.betitarev.activities.MainActivity;
import com.course.betitarev.betitarev.objects.Mail;
import com.course.betitarev.betitarev.objects.Player;
import com.course.betitarev.betitarev.objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

/**
 * The signup activity to register a new User.
 */
public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputName, inputFamilyName;  // All the EditText for the activity.
    private ProgressBar progressBar;  // The progress bar to wait for the connection.
    private FirebaseAuth auth;  // The Firebase authentication.
    private DatabaseReference mFirebaseDatabase;  // The database reference.

    /**
     * The function on create is call every time we create this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        Button btnSignIn = findViewById(R.id.sign_in_button);
        Button btnSignUp = findViewById(R.id.sign_up_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputName = findViewById(R.id.name);
        inputFamilyName = findViewById(R.id.family_name);
        progressBar = findViewById(R.id.progressBar);
        // The three buttons.
        Button btnResetPassword = findViewById(R.id.btn_reset_password);

        // The database instance.
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String name = inputName.getText().toString().trim();
                final String familyName = inputFamilyName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(familyName)) {
                    Toast.makeText(getApplicationContext(), "Enter family name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    FirebaseInstanceId.getInstance().getInstanceId()
                                            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                    if (!task.isSuccessful()) {
                                                        Log.w("SignupActivity", "getInstanceId failed", task.getException());
                                                        return;
                                                    }
                                                    // Get new Instance ID token
                                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                    createUser(name, familyName, new Mail(email), task.getResult().getToken());
                                                    finish();
                                                    // Log and toast
                                                }
                                            });

                                }
                            }
                        });
            }
        });

    }

    /**
     * Creating new user node under 'users'.
     *
     * @param name
     * @param familyName
     * @param email
     * @param pushToken
     */
    private void createUser(String name, String familyName, Mail email, String pushToken) {
        // The user ID.
        String userId = mFirebaseDatabase.push().getKey();
        User user = new Player(name, familyName, email, userId, pushToken);
        assert userId != null;
        mFirebaseDatabase.child(userId).setValue(user);
    }

    /**
     * This function set the visibility of the progress bar to gone.
     */
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
package com.example.betitarev.betitarev.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.fragment.OpenedBetActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.example.betitarev.betitarev.fragment.ProfileActivity;
import com.example.betitarev.betitarev.fragment.StatisticsActivity;
import com.example.betitarev.betitarev.libraries.FireBaseQuery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String dataTitle, dataMessage;
    FirebaseDatabase database;
    public static DatabaseReference myRef;
    public static String title, message;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_place_bet:
                    fragment = new PlaceBetActivity();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_opened_bet:
                    fragment = new OpenedBetActivity();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_statistics:
                    fragment = new StatisticsActivity();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = ProfileActivity.newInstance();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FireBaseQuery.loadCurrentUser(FireBaseQuery.getCurrentMail(), this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                    }
                });

        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("title")) {
                    dataTitle = (String) getIntent().getExtras().get(key);
                }
                if (key.equals("message")) {
                    dataMessage = (String) getIntent().getExtras().get(key);
                }
            }
            showAlertDialog();
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");
        sendMessage();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage("title: " + dataTitle + "\n" + "message: " + dataMessage);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void subscribeToTopic(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic("notifications");
        Toast.makeText(this, "Subscribed to Topic: Notifications", Toast.LENGTH_SHORT).show();
    }

    public void sendMessage() {
        myRef.push().setValue(message + " " + title);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }

    public void begin() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new PlaceBetActivity());
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}

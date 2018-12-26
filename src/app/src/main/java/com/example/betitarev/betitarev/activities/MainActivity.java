package com.example.betitarev.betitarev.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.fragment.OpenedBetActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.example.betitarev.betitarev.fragment.ProfileActivity;
import com.example.betitarev.betitarev.fragment.StatisticsActivity;
import com.example.betitarev.betitarev.helper.FragmentHelper;
import com.example.betitarev.betitarev.libraries.FireBaseQuery;

public class MainActivity extends AppCompatActivity {

    private static AppCompatActivity activity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_place_bet:
                    fragment = new PlaceBetActivity();
                    FragmentHelper.loadFragment(fragment);
                    return true;
                case R.id.navigation_opened_bet:
                    fragment = new OpenedBetActivity();
                    FragmentHelper.loadFragment(fragment);
                    return true;
                case R.id.navigation_statistics:
                    fragment = new StatisticsActivity();
                    FragmentHelper.loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileActivity();
                    FragmentHelper.loadFragment(fragment);
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
    }

    public void begin() {
        activity = this;
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentHelper.loadFragment(new PlaceBetActivity());
    }


    public static AppCompatActivity getActivity() {
        return activity;
    }

}

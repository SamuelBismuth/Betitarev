package com.example.betitarev.betitarev.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.fragment.OpenedBetActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.example.betitarev.betitarev.fragment.ProfileActivity;
import com.example.betitarev.betitarev.fragment.StatisticsActivity;
import com.example.betitarev.betitarev.libraries.FireBaseQuery;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FireBaseQuery.loadCurrentUser(FireBaseQuery.getCurrentMail());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new PlaceBetActivity());
    }


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
                    fragment = new ProfileActivity();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

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

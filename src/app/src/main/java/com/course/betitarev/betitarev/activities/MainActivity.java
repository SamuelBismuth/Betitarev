package com.course.betitarev.betitarev.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.course.betitarev.betitarev.R;
import com.course.betitarev.betitarev.fragment.OpenedBetActivity;
import com.course.betitarev.betitarev.fragment.PlaceBetActivity;
import com.course.betitarev.betitarev.fragment.ProfileActivity;
import com.course.betitarev.betitarev.fragment.StatisticsActivity;
import com.course.betitarev.betitarev.helper.FireBaseQuery;
import com.course.betitarev.betitarev.helper.FragmentHelper;

import org.apache.commons.io.FileUtils;

/**
 * The main activity, actually handle four fragments.
 */
public class MainActivity extends AppCompatActivity {

    private static AppCompatActivity activity;  // This implementation allows developper to get an unlimited access to the main activity.
    private ProgressBar progressBar;  // The progress bar to wait for the connection.

    /**
     * This function detect the on click of the fragment and make what is necessary to make.
     */
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

    /**
     * This static function return the main activity.
     * This implementation allows developers to get an access to the main activity from every fragments.
     *
     * @return MainActivity
     */
    public static AppCompatActivity getActivity() {
        return activity;
    }

    /**
     * The function on create is call every time we create this activity.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBarMain);
        progressBar.setVisibility(View.VISIBLE);
        FileUtils.deleteQuietly(getBaseContext().getCacheDir());
        FireBaseQuery.loadInitialData(FireBaseQuery.getCurrentMail(), this);
    }

    /**
     * This function is called after the data is completely loaded.
     */
    public void begin() {
        activity = this;
        progressBar.setVisibility(View.GONE);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentHelper.loadFragment(new PlaceBetActivity());
    }

}

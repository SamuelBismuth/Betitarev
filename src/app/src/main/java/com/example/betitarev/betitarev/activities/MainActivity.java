package com.example.betitarev.betitarev.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.fragment.OpenedBetActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.example.betitarev.betitarev.fragment.ProfileActivity;
import com.example.betitarev.betitarev.fragment.StatisticsActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private String TAG = MainActivity.class.getSimpleName();
    String[] arrays = new String[]{"98411", "98422", "98433", "98444", "98455"};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // load the store fragment by default
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
                    runPlaceBet();
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

    private void runPlaceBet() {
        Toolbar tbMainSearch = (Toolbar) findViewById(R.id.toolbar);
        ListView lvToolbarSearch = (ListView) findViewById(R.id.toolbarsearch);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrays);
        Log.e("hello", lvToolbarSearch.toString());
        lvToolbarSearch.setAdapter(adapter);
        setSupportActionBar(tbMainSearch);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem mSearchmenuItem = menu.findItem(R.id.menu_toolbarsearch);
        SearchView searchView = (SearchView) mSearchmenuItem.getActionView();
        searchView.setQueryHint("enter Text");
        searchView.setOnQueryTextListener(this  );
        Log.d(TAG, "onCreateOptionsMenu: mSearchmenuItem->" + mSearchmenuItem.getActionView());
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "onQueryTextSubmit: query->"+query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "onQueryTextChange: newText->" + newText);
        adapter.getFilter().filter(newText);
        return true;
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

package com.example.betitarev.betitarev.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;

public class PlaceBetActivity extends Fragment {

    private SearchView searchFriend, searchArbitrator;
    private EditText betPhrase, betValue;
    private RadioButton withArbitrator, withoutArbitrator;

    private SearchView.OnQueryTextListener queryTextListener;

    public PlaceBetActivity() {
        // Required empty public constructor
    }

    public static PlaceBetActivity newInstance(String param1, String param2) {
        PlaceBetActivity fragment = new PlaceBetActivity();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("onQueryTextChange", "test");
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchFriend = (SearchView) searchItem.getActionView();
        }
        if (searchFriend != null) {
            searchFriend.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    return true;
                }
            };
            searchFriend.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchFriend.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_bet, container, false);
        searchArbitrator = view.findViewById(R.id.search_arbitrator);
        searchFriend = view.findViewById(R.id.search_friend);
        betPhrase = view.findViewById(R.id.bet_phrase);
        betValue = view.findViewById(R.id.bet_value);
        withArbitrator = view.findViewById(R.id.with_arb);
        withoutArbitrator = view.findViewById(R.id.without_arb);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        Button sendRequestButton = (Button) view.findViewById(R.id.send_request);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.with_arb:
                        searchArbitrator.setVisibility(View.VISIBLE);
                        break;
                    case R.id.without_arb:
                        searchArbitrator.setVisibility(View.GONE);
                        break;
                }
            }
        });
        sendRequestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Clear everything, send the request, and move to another fragment maybe?
                if(isInRules(v)) {
                    sendNotification(v);
                    clearAll(v);
                }
                else
                    Toast.makeText(getActivity(), "You need to fill all the fields!", Toast.LENGTH_SHORT).show();
                Log.i("Onclick", "Need to implement it");
            }
        });
        return view;
    }

    private boolean isInRules(View v) {
        return true;
    }

    private void sendNotification(View v) {
    }

    private void clearAll(View v) {
    }

    public SearchView getSearchFriend() {
        return searchFriend;
    }

    public SearchView getSearchArbitrator() {
        return searchArbitrator;
    }

    public EditText getBetPhrase() {
        return betPhrase;
    }

    public EditText getBetValue() {
        return betValue;
    }

    public RadioButton getWithArbitrator() {
        return withArbitrator;
    }

    public RadioButton getWithoutArbitrator() {
        return withoutArbitrator;
    }
}

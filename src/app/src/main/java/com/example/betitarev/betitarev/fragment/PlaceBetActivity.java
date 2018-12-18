package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.betitarev.betitarev.R;

public class PlaceBetActivity extends Fragment {

    private SearchView searchFriend, searchArbitrator;
    private EditText betPhrase, betValue;
    private RadioButton withArbitrator, withoutArbitrator;


    public PlaceBetActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_bet, container, false);
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
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear everything, send the request, and move to another fragment maybe?
                if (isInRules(v)) {
                    sendNotification(v);
                    clearAll(v);
                } else
                    Toast.makeText(getActivity(), "You need to fill all the fields!", Toast.LENGTH_SHORT).show();
                Log.i("Onclick", "Need to implement it");
            }
        });
        return view;
    }

    /**
     * This method check if either all the required files are full or not.
     * The required fields are:
     * - The search view add friend.
     * - The bet phrase.
     * - The bet value.
     * - The radio button either on without or with arbitrator.
     * - If the radio button on with arbitrator, the seach view add arbitrator is also required.
     *
     * @param view
     * @return true is all the fields are full, else false.
     */
    private boolean isInRules(View view) {
        return true;
    }

    private void sendNotification(View view) {
    }

    private void clearAll(View view) {
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

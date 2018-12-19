package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.libraries.FireBaseQuery;
import com.example.betitarev.betitarev.objects.Player;

public class PlaceBetActivity extends Fragment {

    ArrayAdapter<String> adapterFriend, adapterArbitrator;
    private EditText betPhrase, betValue;
    private RadioButton withArbitrator, withoutArbitrator;
    private ListView listOfFriend, listOfArbitrator;
    private EditText searchFriend, searchArbitrator;
    private Player player;

    public PlaceBetActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = FireBaseQuery.getPlayer()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_bet, container, false);
        betPhrase = view.findViewById(R.id.bet_phrase);
        betValue = view.findViewById(R.id.bet_value);
        withArbitrator = view.findViewById(R.id.with_arb);
        withoutArbitrator = view.findViewById(R.id.without_arb);
        listOfFriend = view.findViewById(R.id.list_friend);
        searchFriend = view.findViewById(R.id.search_friend);
        listOfArbitrator = view.findViewById(R.id.list_arbitrator);
        searchArbitrator = view.findViewById(R.id.search_arbitrator);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        Button sendRequestButton = view.findViewById(R.id.send_request);

        String friends[] = {"Yishayito", "Samyyy", "Jonato", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
        adapterFriend = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.user_name, friends);
        listOfFriend.setAdapter(adapterFriend);
        searchFriend.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                PlaceBetActivity.this.adapterFriend.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        String arbitrators[] = {"Yishayito", "Samyyy", "Jonato", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
        adapterArbitrator = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.user_name, arbitrators);
        listOfArbitrator.setAdapter(adapterArbitrator);
        searchArbitrator.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                PlaceBetActivity.this.adapterArbitrator.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }
            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.with_arb:
                        listOfArbitrator.setVisibility(View.VISIBLE);
                        searchArbitrator.setVisibility(View.VISIBLE);
                        break;
                    case R.id.without_arb:
                        listOfArbitrator.setVisibility(View.GONE);
                        searchArbitrator.setVisibility(View.GONE);
                        break;
                }
            }
        });

        listOfFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchFriend.setText(listOfFriend.getItemAtPosition(0).toString());
                searchFriend.clearFocus();
                betPhrase.requestFocus();
            }
        });

        listOfArbitrator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchArbitrator.setText(listOfArbitrator.getItemAtPosition(0).toString());
                listOfArbitrator.clearFocus();
            }
        });

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear everything, send the request, and move to another fragment maybe?
                if (isInRules()) {
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
     * @return true is all the fields are full, else false.
     */
    private boolean isInRules() {
        return true;
    }

    private void sendNotification(View view) {
    }

    private void clearAll(View view) {
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

    public ArrayAdapter<String> getAdapterFriend() {
        return adapterFriend;
    }

    public ArrayAdapter<String> getAdapterArbitrator() {
        return adapterArbitrator;
    }

    public EditText getSearchFriend() {
        return searchFriend;
    }

    public ListView getListOfFriend() {
        return listOfFriend;
    }

    public ListView getListOfArbitrator() {
        return listOfArbitrator;
    }
}

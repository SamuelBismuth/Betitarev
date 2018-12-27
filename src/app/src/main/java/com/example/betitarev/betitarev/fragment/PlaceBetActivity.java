package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.betitarev.betitarev.helper.FireBaseQuery;
import com.example.betitarev.betitarev.helper.FragmentHelper;
import com.example.betitarev.betitarev.objects.CurrentPlayer;
import com.example.betitarev.betitarev.objects.Friend;
import com.example.betitarev.betitarev.objects.Notification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class PlaceBetActivity extends Fragment {

    ArrayAdapter<String> adapterFriend, adapterArbitrator;
    private EditText betPhrase, betValue;
    private RadioButton withArbitrator, withoutArbitrator;
    private ListView listOfFriend, listOfArbitrator;
    private EditText searchFriend, searchArbitrator;
    private Friend bettor, arbitrator;
    private boolean isWithArb;

    public PlaceBetActivity() {
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
        listOfFriend = view.findViewById(R.id.list_friend);
        searchFriend = view.findViewById(R.id.search_friend);
        listOfArbitrator = view.findViewById(R.id.list_arbitrator);
        searchArbitrator = view.findViewById(R.id.search_arbitrator);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        Button sendRequestButton = view.findViewById(R.id.send_request);
        List<String> friends = new ArrayList<>();

        isWithArb = false;

        for (Friend friend : CurrentPlayer.getInstance().getFriends().getFriends())
            friends.add(friend.getFullName());

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
        adapterArbitrator = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.user_name, friends);
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
                        isWithArb = true;
                        break;
                    case R.id.without_arb:
                        listOfArbitrator.setVisibility(View.GONE);
                        searchArbitrator.setVisibility(View.GONE);
                        isWithArb = false;
                        break;
                }
            }
        });

        listOfFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchFriend.setText(listOfFriend.getItemAtPosition(i).toString());
                searchFriend.clearFocus();
                betPhrase.requestFocus();
            }
        });

        listOfArbitrator.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchArbitrator.setText(listOfArbitrator.getItemAtPosition(i).toString());
                listOfArbitrator.clearFocus();
            }
        });

        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear everything, send the request, and move to another fragment maybe?
                if (isInRules()) {
                    pushDataBase();
                    clearAll(v);
                } else
                    Toast.makeText(getActivity(), "You need to fill all the fields!", Toast.LENGTH_SHORT).show();
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
        boolean arb = false, play = false;
        if (this.getBetPhrase().getText() == null)
            return false;
        if (this.getWithArbitrator().isChecked()) {
            for (Friend friend : CurrentPlayer.getInstance().getFriends().getFriends()) {
                if (friend.getFullName().equals(listOfFriend.getItemAtPosition(0))) {
                    play = true;
                    bettor = friend;
                }
                if (friend.getFullName().equals(listOfArbitrator.getItemAtPosition(0))) {
                    arb = true;
                    arbitrator = friend;
                }
            }
            return play & arb;
        }
        if (this.getWithoutArbitrator().isChecked())
            for (Friend player : CurrentPlayer.getInstance().getFriends().getFriends())
                if (player.getFullName().equals(listOfFriend.getItemAtPosition(0))) {
                    bettor = player;
                    return true;
                }
        return false;
    }

    private void pushDataBase() {
        String bettor2 = bettor.getFullName();
        if (isWithArb) {
            String arb = arbitrator.getFullName();
            FireBaseQuery.placeNewBetWithArb(bettor2, arb, betPhrase.getText().toString(), betValue.getText().toString());
        } else {
            FireBaseQuery.placeNewBetWithoutArb(bettor2, betPhrase.getText().toString(), betValue.getText().toString());
        }
    }

    private void clearAll(View view) {
        BottomNavigationView navigation = getActivity().findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_opened_bet);
        FragmentHelper.loadFragment(new OpenedBetActivity());
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
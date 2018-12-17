package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.example.betitarev.betitarev.R;

public class PlaceBetActivity extends Fragment {

    private RadioGroup radioGroup;
    private SearchView searchArbitrator;
    private Button sendRequestButton;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_bet, container, false);
        searchArbitrator = view.findViewById(R.id.search_arbitrator);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        sendRequestButton = (Button) view.findViewById(R.id.send_request);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.with_arb:
                        searchArbitrator.setVisibility(View.VISIBLE);
                        break;
                    case R.id.without_arb:
                        searchArbitrator.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
        sendRequestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("Onclick", "Need to implement it");
                // Clear everything, send the request, and move to another fragment maybe?
            }
        });
        return view;
    }

}

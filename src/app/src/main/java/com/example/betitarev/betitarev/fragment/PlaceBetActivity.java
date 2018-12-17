package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betitarev.betitarev.R;

public class PlaceBetActivity extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_bet, container, false);
        //SearchView searchView = (SearchView) view.findViewById(R.id.search_friend);
        // Inflate the layout for this fragment
        return view;
    }

}

package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_place_bet, container, false);
    }

}

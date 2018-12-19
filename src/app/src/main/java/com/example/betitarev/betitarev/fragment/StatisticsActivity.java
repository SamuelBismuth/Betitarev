package com.example.betitarev.betitarev.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.betitarev.betitarev.R;

public class StatisticsActivity extends Fragment {

    public StatisticsActivity() {
        // Required empty public constructor
    }

    public static StatisticsActivity newInstance(String param1, String param2) {
        StatisticsActivity fragment = new StatisticsActivity();
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
        return inflater.inflate(R.layout.activity_statistics, container, false);
    }

}

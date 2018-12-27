package com.example.betitarev.betitarev.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.objects.Bet;

import java.util.List;


public class OpenedBetActivity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Bet> bets;

    public OpenedBetActivity() {
        // Required empty public constructor
    }

    public static OpenedBetActivity newInstance(String param1, String param2) {
        OpenedBetActivity fragment = new OpenedBetActivity();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
     * Bundle savedInstanceState) {
     * <p>
     * View view=inflater.inflate(R.layout.activity_opened_bet, container, false);
     * mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
     * <p>
     * // use this setting to improve performance if you know that changes
     * // in content do not change the layout size of the RecyclerView
     * mRecyclerView.setHasFixedSize(true);
     * <p>
     * // use a linear layout manager
     * mLayoutManager = new LinearLayoutManager(getActivity());
     * mRecyclerView.setLayoutManager(mLayoutManager);
     * <p>
     * // specify an adapter (see also next example)
     * mAdapter = new Adapter(bets);
     * mRecyclerView.setAdapter(mAdapter);
     * <p>
     * // Inflate the layout for this fragment
     * return inflater.inflate(R.layout.activity_opened_bet, container, false);
     * }
     **/
    /////////////////////////////////////////////////////////////////////////////////////////////
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_opened_bet, container, false);
        RelativeLayout myLayout = (RelativeLayout) view.findViewById(R.id.rl);
        //LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < 10; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText("jjjdjdjd\n");
            tv.setTextSize(42);
            tv.setTextColor(Color.RED);
            tv.setGravity(i);
            tv.setBackgroundColor(Color.BLUE);
            myLayout.addView(tv);
        }
        return view;
    }
}


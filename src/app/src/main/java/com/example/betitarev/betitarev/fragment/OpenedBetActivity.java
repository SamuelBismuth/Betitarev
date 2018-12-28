package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.helper.Adapter;
import com.example.betitarev.betitarev.objects.CurrentUserBets;

/**
 * This fragment is used to deploy all the historic of the activities.
 */
public class OpenedBetActivity extends Fragment {

    /**
     * Required empty public constructor for Firebase.
     */
    public OpenedBetActivity() {
    }

    /**
     * The function on create is call every time we create this fragment.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * This function is called every time the view of the fragment must be created.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_opened_bet, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        RecyclerView.Adapter mAdapter = new Adapter(CurrentUserBets.getInstance().getBets());
        mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return view;
    }


}


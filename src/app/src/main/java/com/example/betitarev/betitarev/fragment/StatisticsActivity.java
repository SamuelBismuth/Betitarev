package com.example.betitarev.betitarev.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.objects.CurrentUser;

public class StatisticsActivity extends Fragment {

    private TextView numberOfWin, numberOfLose, numberOfDraw, numberOfArbitrator;

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
        View view = inflater.inflate(R.layout.activity_statistics, container, false);
        numberOfWin = view.findViewById(R.id.number_of_win);
        numberOfLose = view.findViewById(R.id.number_of_lose);
        numberOfDraw = view.findViewById(R.id.number_of_draw);
        numberOfArbitrator = view.findViewById(R.id.number_of_arbitrator);
        numberOfWin.setText("\n\n"+numberOfWin.getText() + Integer.toString(
                CurrentUser.getInstance().getStatistics().getWinStat().getCounter()));
        numberOfLose.setText("\n\n"+numberOfLose.getText()+Integer.toString(
                CurrentUser.getInstance().getStatistics().getLoseStat().getCounter()));
        numberOfDraw.setText("\n\n"+numberOfDraw.getText()+Integer.toString(
                CurrentUser.getInstance().getStatistics().getDrawStat().getCounter()));
        numberOfArbitrator.setText("\n\n"+numberOfArbitrator.getText()+Integer.toString(
                CurrentUser.getInstance().getStatistics().getArbitratorStat().getCounter()));
        return view;
    }

}

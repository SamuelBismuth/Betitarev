package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.objects.CurrentPlayer;

//for graph
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import android.support.v4.app.FragmentTransaction;






public class StatisticsActivity extends Fragment{

    private TextView numberOfWin, numberOfLose, numberOfDraw, numberOfArbitrator;
    private PieChartView pieChartView;
    private Button btn;
    private TextView tv;

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


        int win=CurrentPlayer.getInstance().getStatistics().getWinStat().getCounter();
        int lose=CurrentPlayer.getInstance().getStatistics().getLoseStat().getCounter();
        int draw=CurrentPlayer.getInstance().getStatistics().getDrawStat().getCounter();
        int arbitrator=CurrentPlayer.getInstance().getStatistics().getArbitratorStat().getCounter();

        //pie chart of participation in bets
        pieChartView = view.findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(win, Color.GREEN).setLabel("win"));
        pieData.add(new SliceValue(lose, Color.RED).setLabel("lose"));
        pieData.add(new SliceValue(draw, Color.YELLOW).setLabel("draw"));
        pieData.add(new SliceValue(arbitrator, Color.BLUE).setLabel("arbitrator"));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Bet's participation").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

        tv = (TextView)view.findViewById(R.id.bet_invite);
        btn = (Button)view.findViewById(R.id.btn);
        //condition: if current player does not participat on bets - invite him to
        if (win==0 && lose==0 && draw==0 && arbitrator==0){
            btn.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
        }else {
            btn.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
        }
        //define on click go to place a bet
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PlaceBetActivity());
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}

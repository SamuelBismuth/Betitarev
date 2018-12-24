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
import com.example.betitarev.betitarev.objects.CurrentUser;
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
//        numberOfWin = view.findViewById(R.id.number_of_win);
//        numberOfLose = view.findViewById(R.id.number_of_lose);
//        numberOfDraw = view.findViewById(R.id.number_of_draw);
//        numberOfArbitrator = view.findViewById(R.id.number_of_arbitrator);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//        //Log.i("samjbwdiywvhfv", Integer.toString(height ));
//        numberOfWin.setTextSize(height * width / 40000);
//        numberOfLose.setTextSize(height * width / 40000);
//        numberOfDraw.setTextSize(height * width / 40000);
//        numberOfArbitrator.setTextSize(height * width / 40000);
//        numberOfWin.setText(numberOfWin.getText()+Integer.toString(
//                CurrentUser.getInstance().getStatistics().getWinStat().getCounter()));
//         numberOfLose.setText(numberOfLose.getText()+Integer.toString(
//                 CurrentUser.getInstance().getStatistics().getLoseStat().getCounter()));
//         numberOfDraw.setText(numberOfDraw.getText()+Integer.toString(
//                 CurrentUser.getInstance().getStatistics().getDrawStat().getCounter()));
//         numberOfArbitrator.setText(numberOfArbitrator.getText()+Integer.toString(
//                 CurrentUser.getInstance().getStatistics().getArbitratorStat().getCounter()));
        btn = (Button)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PlaceBetActivity());
            }
        });
        int win=CurrentUser.getInstance().getStatistics().getWinStat().getCounter();
        int lose=CurrentUser.getInstance().getStatistics().getLoseStat().getCounter();
        int draw=CurrentUser.getInstance().getStatistics().getDrawStat().getCounter();
        int arbitrator=CurrentUser.getInstance().getStatistics().getArbitratorStat().getCounter();
        if(win==0 && lose==0 && draw==0 && arbitrator==0) {
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
        }
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

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
import android.view.View.OnClickListener;
import android.content.Intent;

import com.example.betitarev.betitarev.activities.MainActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.fragment.OpenedBetActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.example.betitarev.betitarev.fragment.ProfileActivity;
import com.example.betitarev.betitarev.fragment.StatisticsActivity;
import com.example.betitarev.betitarev.libraries.FireBaseQuery;
import com.example.betitarev.betitarev.objects.CurrentUser;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;

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
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StatisticsActivity.this, PlaceBetActivity.class));
            }
        });
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


        pieChartView = view.findViewById(R.id.chart);

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(CurrentUser.getInstance().getStatistics().getWinStat().getCounter(), Color.GREEN).setLabel("win"));
        pieData.add(new SliceValue(CurrentUser.getInstance().getStatistics().getLoseStat().getCounter(), Color.RED).setLabel("lose"));
        pieData.add(new SliceValue(CurrentUser.getInstance().getStatistics().getDrawStat().getCounter(), Color.YELLOW).setLabel("draw"));
        pieData.add(new SliceValue(CurrentUser.getInstance().getStatistics().getArbitratorStat().getCounter(), Color.BLUE).setLabel("arbitrator"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Bet's participation").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
        return view;
    }




}

package com.example.betitarev.betitarev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.fragment.PlaceBetActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends Fragment {

    private FirebaseAuth auth;
    private String Name;
    private TextView mNameTextView;


    public ProfileActivity() {
        // Required empty public constructor
    }

    public static ProfileActivity newInstance(String Name) {
        ProfileActivity fragment = new ProfileActivity();
        Bundle args = new Bundle();
        args.putString("Name", Name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }
    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            Name = bundle.getString("Name");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        mNameTextView = (TextView) view.findViewById(R.id.name);
        readBundle(getArguments());
        mNameTextView.setText(String.format("%s", Name));

        // Inflate the layout for this fragment

        return view;

    }


}

package com.example.betitarev.betitarev.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.helper.FireBaseQuery;

public class ConfirmBetActivity extends AppCompatActivity {

    private String betId, dataTitle, dataMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("samyyyy1", "Confirm bet act");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
            } else {
                this.betId = extras.getString("betId");
                this.dataTitle = extras.getString("title");
                this.dataMessage = extras.getString("message");
            }
        }
        showAlertDialog();
    }

    private void showAlertDialog() {
        final EditText input = new EditText(ConfirmBetActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setHint("Enter your guessing:");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage("title: " + dataTitle + "\n" + "message: " + dataMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FireBaseQuery.getBetById(betId, input.getText().toString());
                ConfirmBetActivity.this.finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ConfirmBetActivity.this.finish();
                dialog.dismiss();
            }
        });
        builder.setView(input);
        builder.show();
    }
}

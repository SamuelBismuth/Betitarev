package com.example.betitarev.betitarev.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.betitarev.betitarev.R;

public class ConfirmBetActivity extends AppCompatActivity {

    private String betId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                String Name = null;
            }
            else {
                this.betId = extras.getString("betId");
                showAlertDialog(extras.getString("title"), extras.getString("message"));
            }
        }
    }

    private void showAlertDialog(String dataTitle, String dataMessage) {
        final EditText input = new EditText(ConfirmBetActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setHint("Enter your guessing:");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage("title: " + dataTitle + "\n" + "message: " + dataMessage);
        builder.setPositiveButton("OK", onOk(input));
        builder.setNegativeButton("Reject", OnReject("LUeMOc223fCXIXRVmM4"));
        builder.setView(input);
        builder.show();
    }

    private DialogInterface.OnClickListener OnReject(String betid) {
        Log.i("Sam2", "On reject, we're deleting the bet from the database.");
        Log.i("sam3", betId);
        // need to be redirected to the place a bet activity.

        return null;
    }

    private DialogInterface.OnClickListener onOk(EditText input) {
        Log.i("Sam1", "On ok");
        Log.i("sam4", betId);
        return null;
    }


}

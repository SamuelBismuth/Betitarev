package com.example.betitarev.betitarev.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
=======
import android.view.ContextThemeWrapper;
>>>>>>> 5548262a2af14fdc4b0ac59272f28a7bf26cfc84

import com.example.betitarev.betitarev.R;

public class ConfirmBetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {

            }
            else {
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
        builder.setNegativeButton("Reject", OnReject());
        builder.setView(input);
        builder.show();
    }

    private DialogInterface.OnClickListener OnReject() {
        Log.i("Sam2", "On reject, we're deleting the bet from the database.");
        // need to be redirected to the place a bet activity.
        return null;
    }

    private DialogInterface.OnClickListener onOk(EditText input) {
        Log.i("Sam1", "On ok");

        return null;
    }


}

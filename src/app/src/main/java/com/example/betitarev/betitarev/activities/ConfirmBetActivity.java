package com.example.betitarev.betitarev.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.helper.FireBaseQuery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Here is where the {@link com.example.betitarev.betitarev.objects.Player} is redirected when he accepts a bet.
 */
public class ConfirmBetActivity extends AppCompatActivity {

    private String betId, dataTitle, dataMessage;  // The Strings of the notification.

    /**
     * The function on create is call every time we create this activity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.betId = extras.getString("betId");
                this.dataTitle = extras.getString("title");
                this.dataMessage = extras.getString("message");
            }
        }
        if (dataTitle.contains("Bet"))
            showAlertDialogPlayer();
        else
            showAlertDialogArb();
    }

    /**
     * This is the alert dialog for the {@link com.example.betitarev.betitarev.objects.Arbitrator}.
     */
    private void showAlertDialogArb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Answer");
        builder.setMessage("title: " + dataTitle + "\n" + "message: " + dataMessage);
        builder.setPositiveButton("Player 1", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Winner", "the winner is the player 1");
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
                reference.child(betId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String userIdWinner = dataSnapshot.child("player1/user/userid").getValue().toString();
                        String userIdLoser = dataSnapshot.child("player2/user/userid").getValue().toString();
                        String winnerName = dataSnapshot.child("player1/user/name").getValue().toString() + " " + dataSnapshot.child("player1/user/familyName").getValue().toString();
                        reference.child(betId).child("winner").setValue(winnerName);
                        FireBaseQuery.updateStats(userIdWinner, 0);
                        FireBaseQuery.updateStats(userIdLoser, 1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
                ConfirmBetActivity.this.finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Player 2", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Winner", "the winner is the player 2");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
                reference.child(betId).child("winner").setValue("Player 2");
                reference.child(betId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String userIdWinner = dataSnapshot.child("player2/user/userid").getValue().toString();
                        String userIdLoser = dataSnapshot.child("player1/user/userid").getValue().toString();
                        FireBaseQuery.updateStats(userIdWinner, 0);
                        FireBaseQuery.updateStats(userIdLoser, 1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
                ConfirmBetActivity.this.finish();
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLUE);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.child(betId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userIdArbitrator = dataSnapshot.child("arbitrator/user/userid").getValue().toString();
                Log.i("arbitest", "im updating arbitrator");
                FireBaseQuery.updateStats(userIdArbitrator, 2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    /**
     * This is the alert dialog for the {@link com.example.betitarev.betitarev.objects.Player}.
     */
    private void showAlertDialogPlayer() {
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
        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLUE);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);
    }

}

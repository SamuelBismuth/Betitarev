package com.example.betitarev.betitarev.helper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.objects.Arbitrator;
import com.example.betitarev.betitarev.objects.Bet;
import com.example.betitarev.betitarev.objects.BetStatus;
import com.example.betitarev.betitarev.objects.BetWithArbitrator;
import com.example.betitarev.betitarev.objects.BetWithoutArbitrator;
import com.example.betitarev.betitarev.objects.Bettor;
import com.example.betitarev.betitarev.objects.BettorStatus;
import com.example.betitarev.betitarev.objects.CurrentPlayer;
import com.example.betitarev.betitarev.objects.CurrentUserBets;
import com.example.betitarev.betitarev.objects.FictiveMoney;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.Notification;
import com.example.betitarev.betitarev.objects.Player;
import com.example.betitarev.betitarev.objects.User;
import com.example.betitarev.betitarev.objects.UsersNamesHashmap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.example.betitarev.betitarev.objects.BetStatus.WaitForOne;

public class FireBaseQuery {

    private static String userid, name1, familyName1;
    private static Player user;
    private static FirebaseAuth auth;
    private static Set<User> allUsersSet = new LinkedHashSet<>();
    private static List<Bet> bets = new ArrayList<>();
    private static FirebaseStorage storage;
    private static StorageReference storageReference;

    public static Mail getCurrentMail() {
        auth = FirebaseAuth.getInstance();
        return new Mail(auth.getCurrentUser().getEmail());
    }


    /**
     * Add the statistics and the friends on the database.
     *
     * @param email
     * @param mainActivity
     */
    public static void loadCurrentUser(final Mail email, final MainActivity mainActivity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Player currentUser = datas.getValue(Player.class);
                    Log.e("user", currentUser.toString());
                    if (!currentUser.getMail().getMail().equals(email.getMail()))
                        allUsersSet.add(currentUser);
                }
                UsersNamesHashmap.getInstance(allUsersSet);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    user = datas.getValue(Player.class);
                }

                CurrentPlayer.getInstance(user);
                //loadCurrentBets();
                Log.e("userDetails", user.toString());
                mainActivity.begin();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static void changeBetStatus(String betid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.child(betid).child("status").setValue(2);
        //Log.e("statbet", reference.child(betid).getRoot().toString());
    }

    public static void updateUserFriends(final Context con) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(CurrentPlayer.getInstance().getUserid()).child("friends").setValue(CurrentPlayer.getInstance().getFriends());
        Activity a = (Activity) con;
        a.finish();

    }

    public static void updateUserPictureUri() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/" + CurrentPlayer.getInstance().getMail().getMail() + "/profile");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users/");
                final Uri Furi = uri;
                CurrentPlayer.getInstance().setPicture(Furi.toString());
                reference.child(CurrentPlayer.getInstance().getUserid()).child("picture").setValue(Furi.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("downloadImage", "failed");
            }
        });
    }

    public static void placeNewBetWithArb(String bettor2, String arb, String betPhrase, String betValue, String betGuessing) {
        Bet bet = new BetWithArbitrator(new Bettor(CurrentPlayer.getInstance(), BettorStatus.Confirmed, betGuessing),
                new Bettor((Player) UsersNamesHashmap.getAllKeysForValue(bettor2).get(0), BettorStatus.NotConfirmed, " "),
                betPhrase, new FictiveMoney(Integer.parseInt(betValue)),
                new Arbitrator(UsersNamesHashmap.getAllKeysForValue(arb).get(0)), BetStatus.WaitForTwo);
        DatabaseReference betsReference = FirebaseDatabase.getInstance().getReference("bets");
        String betId = betsReference.push().getKey();
        betsReference.child(betId).setValue(bet);
        CurrentUserBets.getInstance().addBet(bet);
        createNotification(bet, betId, true);
    }

    public static void placeNewBetWithoutArb(String bettor2, String betPhrase, String betValue, String betGuessing) {
        Bet bet = new BetWithoutArbitrator(new Bettor(CurrentPlayer.getInstance(), BettorStatus.Confirmed, betGuessing),
                new Bettor((Player) UsersNamesHashmap.getAllKeysForValue(bettor2).get(0), BettorStatus.NotConfirmed, " "),
                betPhrase, new FictiveMoney(Integer.parseInt(betValue)), WaitForOne);
        DatabaseReference betsReference = FirebaseDatabase.getInstance().getReference("bets");
        String betId = betsReference.push().getKey();
        betsReference.child(betId).setValue(bet);
        CurrentUserBets.getInstance().addBet(bet);
        createNotification(bet, betId, false);
    }

    private static void createNotification(Bet bet, String betId, boolean flag) {
        sendMessage(new Notification("Bet Request",
                bet.getPhrase(),
                bet.getPlayer1().getUser().getPushToken(),
                bet.getPlayer2().getUser().getPushToken(),
                betId));
        if (flag)
            sendMessage(new Notification("Arbitrator Request",
                    bet.getPlayer1().getUser().getName() + " " + bet.getPlayer1().getUser().getFamilyName() +
                            " want to bet against " + bet.getPlayer2().getUser().getName() + " " + bet.getPlayer2().getUser().getFamilyName()
                            + " on " + bet.getPhrase(),
                    bet.getPlayer1().getUser().getPushToken(),
                    bet.getPlayer2().getUser().getPushToken(),
                    betId));
    }

    public static void sendMessage(Notification notif) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("notifcations");
        String notifId = mFirebaseDatabase.push().getKey();
        mFirebaseDatabase.child(notifId).setValue(notif);
    }

    public static void removeUser(String userid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Log.e("in removeUser", userid + " this is userid");
        reference.child(userid).removeValue();
    }

    public static void getBetById(final String betId, final String value) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bet : dataSnapshot.getChildren()) {
                    if (bet.getKey().equals(betId)) {
                        setBetValue(bet.getValue(Bet.class), value, betId);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void setBetValue(Bet bet, String value, String betId) {
        Log.d("value2", value);
        Log.d("betId2", betId);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.child(betId).child("player2/value").setValue(value);
    }

    private static void sendMessageForTheAnswer() {
        Log.i("Accepted", "Everyone accepted we want the arbitrator confirm the bet");
    }

    private static void loadCurrentBets() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Bet bet = datas.getValue(Bet.class);
                    if (bet.getPlayer1().getUser().getMail().getMail().equals(CurrentPlayer.getInstance().getMail().getMail()) || bet.getPlayer2().getUser().getMail().getMail().equals(CurrentPlayer.getInstance().getMail().getMail())) {
                        bets.add(bet);
                        Log.e("bet added", bet.toString());
                    }
                    if (bet.getArbitrator() != null) {
                        if (bet.getArbitrator().getUser().getMail().getMail().equals(CurrentPlayer.getInstance().getMail().getMail())) {
                            bets.add(bet);
                            Log.e("bet added", bet.toString());
                        }
                    }
                }
                CurrentUserBets.getInstance(bets);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


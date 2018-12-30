package com.example.betitarev.betitarev.helper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.betitarev.betitarev.activities.MainActivity;
import com.example.betitarev.betitarev.objects.Arbitrator;
import com.example.betitarev.betitarev.objects.Bet;
import com.example.betitarev.betitarev.objects.BetWithArbitrator;
import com.example.betitarev.betitarev.objects.BetWithoutArbitrator;
import com.example.betitarev.betitarev.objects.Bettor;
import com.example.betitarev.betitarev.objects.CurrentPlayer;
import com.example.betitarev.betitarev.objects.CurrentUserBets;
import com.example.betitarev.betitarev.objects.FictiveMoney;
import com.example.betitarev.betitarev.objects.Mail;
import com.example.betitarev.betitarev.objects.Notification;
import com.example.betitarev.betitarev.objects.Player;
import com.example.betitarev.betitarev.objects.User;
import com.example.betitarev.betitarev.objects.UsersNamesHashmap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * This class handle queries for the firabase database.
 */
public class FireBaseQuery {

    private static Player user;
    private static Set<User> allUsersSet;
    private static List<Bet> bets;

    /**
     * This function return the mail of the current {@link User}.
     *
     * @return the {@link Mail}.
     */
    public static Mail getCurrentMail() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return new Mail(auth.getCurrentUser().getEmail());
    }

    /**
     * Add the statistics and the friends on the database.
     *
     * @param email
     * @param mainActivity
     */
    public static void loadInitialData(final Mail email, final MainActivity mainActivity) {
        allUsersSet = new LinkedHashSet<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Player currentUser = datas.getValue(Player.class);
                    if (!currentUser.getMail().getMail().equals(email.getMail()))
                        allUsersSet.add(currentUser);
                }
                UsersNamesHashmap.getInstance(allUsersSet);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //And than we collect the data of the current player and store it in a singleton call CurrentPlayer that extends Player
        reference.orderByChild("mail/mail").equalTo(email.getMail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot datas : dataSnapshot.getChildren()) {
                    user = datas.getValue(Player.class);
                }
                CurrentPlayer.getInstance(user);
                loadCurrentBets();
                reloadToken();
                mainActivity.begin();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private static void reloadToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        } else {
                            Log.i("Token", task.getResult().getToken());
                            CurrentPlayer.getInstance().setPushToken(task.getResult().getToken());
                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                            reference.child(CurrentPlayer.getInstance().getUserid()).child("pushToken").setValue(task.getResult().getToken());
                        }
                    }
                });
    }

    /**
     * This function reload the {@link com.example.betitarev.betitarev.objects.Friends} of the {@link User} into the database
     *
     * @param con
     * @param CurrentFriend
     */
    public static void updateUserFriends(final Context con, User CurrentFriend) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(CurrentPlayer.getInstance().getUserid()).child("friends").setValue(CurrentPlayer.getInstance().getFriends());
        reference.child(CurrentFriend.getUserid()).child("friends").setValue(CurrentFriend.getFriends());
        Activity a = (Activity) con;
        a.finish();
    }

    /**
     * This function update the picture.
     */
    public static void updateUserPictureUri() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
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

    /**
     * This function place a new bet with {@link Arbitrator}.
     *
     * @param bettor2
     * @param arb
     * @param betPhrase
     * @param betValue
     * @param betGuessing
     */
    public static void placeNewBetWithArb(String bettor2, String arb, String betPhrase, String betValue, String betGuessing) {
        Bet bet = new BetWithArbitrator(new Bettor(CurrentPlayer.getInstance(), betGuessing),
                new Bettor((Player) UsersNamesHashmap.getAllKeysForValue(bettor2).get(0), " "),
                betPhrase, new FictiveMoney(Integer.parseInt(betValue)),
                new Arbitrator(UsersNamesHashmap.getAllKeysForValue(arb).get(0)));
        DatabaseReference betsReference = FirebaseDatabase.getInstance().getReference("bets");
        String betId = betsReference.push().getKey();
        betsReference.child(betId).setValue(bet);
        CurrentUserBets.getInstance().addBet(bet);
        createNotification(bet, betId);
    }

    /**
     * This function place a new bet without {@link Arbitrator}.
     *
     * @param bettor2
     * @param betPhrase
     * @param betValue
     * @param betGuessing
     */
    public static void placeNewBetWithoutArb(String bettor2, String betPhrase, String betValue, String betGuessing) {
        Bet bet = new BetWithoutArbitrator(new Bettor(CurrentPlayer.getInstance(), betGuessing),
                new Bettor((Player) UsersNamesHashmap.getAllKeysForValue(bettor2).get(0), " "),
                betPhrase, new FictiveMoney(Integer.parseInt(betValue)));
        DatabaseReference betsReference = FirebaseDatabase.getInstance().getReference("bets");
        String betId = betsReference.push().getKey();
        betsReference.child(betId).setValue(bet);
        CurrentUserBets.getInstance().addBet(bet);
        createNotification(bet, betId);
    }

    /**
     * This method create a notification for a bet request.
     *
     * @param bet
     * @param betId
     */
    private static void createNotification(Bet bet, String betId) {
        sendMessage(new Notification("Bet Request",
                bet.getPhrase(),
                bet.getPlayer1().getUser().getPushToken(),
                bet.getPlayer2().getUser().getPushToken(),
                betId));
    }

    /**
     * This method send the {@link Notification} to the database and a trigger send a push notification to the receiver token.
     *
     * @param notif
     */
    protected static void sendMessage(Notification notif) {
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("notifcations");
        String notifId = mFirebaseDatabase.push().getKey();
        mFirebaseDatabase.child(notifId).setValue(notif);
    }

    /**
     * This method is used by the {@link com.example.betitarev.betitarev.objects.Admin} to remove any {@link User}.
     *
     * @param userid
     */
    public static void removeUser(String userid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Log.e("in removeUser", userid + " this is userid");
        reference.child(userid).removeValue();
    }

    /**
     * This function return the bet by his Id.
     *
     * @param betId
     * @param value
     */
    public static void getBetById(final String betId, final String value) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bet : dataSnapshot.getChildren()) {
                    if (bet.getKey().equals(betId)) {
                        setBetValue(bet.getValue(Bet.class), value, betId);
                        CurrentUserBets.getInstance().addBet(bet.getValue(Bet.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This function set the bet guessing.
     *
     * @param bet
     * @param value
     * @param betId
     */
    private static void setBetValue(Bet bet, String value, String betId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.child(betId).child("player2/guessing").setValue(value);
        bet.getPlayer2().setGuessing(value);
        MyFirebaseMessagingService.sendMessageForTheAnswer(bet, betId);

    }

    /**
     * This method load all the current bets.
     */
    private static void loadCurrentBets() {
        bets = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("bets");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Bet bet = datas.getValue(Bet.class);
                    if (bet.getPlayer1().getUser().getMail().getMail().equals(CurrentPlayer.getInstance()
                            .getMail().getMail()) || bet.getPlayer2().getUser().getMail().getMail().
                            equals(CurrentPlayer.getInstance().getMail().getMail())) {
                        bets.add(bet);
                        Log.i("bet added", bet.getPhrase());
                    } else if (bet.getArbitrator() != null) {
                        if (bet.getArbitrator().getUser().getMail().getMail().equals(CurrentPlayer.getInstance().getMail().getMail())) {
                            bets.add(bet);
                            Log.i("bet added", bet.toString());
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

    /**
     * This method push the stats in the database.
     *
     * @param userid
     * @param status
     */
    public static void updateStats(final String userid, final int status) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    if (datas.getKey().equals(userid)) {
                        Player currentUser = datas.getValue(Player.class);
                        switch (status) {
                            case 0:
                                reference.child(userid).child("statistics/winStat/counter").setValue((currentUser.getStatistics().getWinStat().getCounter() + 1));
                                break;
                            case 1:
                                reference.child(userid).child("statistics/loseStat/counter").setValue((currentUser.getStatistics().getLoseStat().getCounter() + 1));
                                break;
                            case 2:
                                Log.d("hereiam", "heene");
                                reference.child(userid).child("statistics/arbitratorStat/counter").setValue((currentUser.getStatistics().getArbitratorStat().getCounter() + 1));
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}


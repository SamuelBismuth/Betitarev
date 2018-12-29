package com.example.betitarev.betitarev.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.ConfirmBetActivity;
import com.example.betitarev.betitarev.objects.Bet;
import com.example.betitarev.betitarev.objects.Notification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * This class handle messages and notifications between {@link com.example.betitarev.betitarev.objects.User}.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * This function send a message to the {@link com.example.betitarev.betitarev.objects.Arbitrator} to get the answer of the bet.
     *
     * @param bet
     * @param betId
     */
    protected static void sendMessageForTheAnswer(Bet bet, String betId) {
        FireBaseQuery.sendMessage(new Notification("Answer for the bet",
                "Phrase: " + bet.getPhrase() + "\n" +
                        "PLayer 1 guesssing: " + bet.getPlayer1().getGuessing() + "\n" +
                        "Player2 guessing: " + bet.getPlayer2().getGuessing(),
                "betitarevToken", bet.getArbitrator().getUser().getPushToken(), betId));
    }

    /**
     * This function is called when the user receive a message.
     *
     * @param remoteMessage
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String notificationTitle = null, notificationBody = null, notificationId = null;
        if (remoteMessage.getData() != null) {
            Log.d("debug", remoteMessage.getData().toString());
            notificationTitle = remoteMessage.getData().get("title");
            notificationBody = remoteMessage.getData().get("body");
            notificationId = remoteMessage.getData().get("id");
        }
        sendNotification(notificationTitle, notificationBody, notificationId);
    }

    /**
     * This function push the notification.
     *
     * @param notificationTitle
     * @param notificationBody
     * @param betId
     */
    private void sendNotification(String notificationTitle, String notificationBody, String betId) {
        Log.i("send notification", "here");
        Intent intent = new Intent(this, ConfirmBetActivity.class);
        intent.putExtra("title", notificationTitle);
        intent.putExtra("message", notificationBody);
        intent.putExtra("betId", betId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}


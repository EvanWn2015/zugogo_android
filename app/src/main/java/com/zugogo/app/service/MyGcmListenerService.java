package com.zugogo.app.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.zugogo.app.view.activity.IntroActivity;

public class MyGcmListenerService extends GcmListenerService {
    private static final String TAG = MyGcmListenerService.class.getSimpleName();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d(TAG, "data: " + data);
        Log.d(TAG, "from: " + from);
        int wakelockid = 1;
        String title = data.getString("title");
        String message = data.getString("message");
        sendNotification(title, message, wakelockid);

//        try {
//            title = URLDecoder.decode(data.getString("title"), "UTF-8");
//            message = URLDecoder.decode(data.getString("message"), "UTF-8");
//            String contentTitle = title;
//            String contentText = title;
//            sendNotification(contentTitle, contentText, wakelockid);
//        } catch (UnsupportedEncodingException e) {
//            sendNotification("Error", message, 0);
//        } catch (Exception e) {
//            sendNotification("Error", message, 0);
//        }
    }

    private void sendNotification(String contentTitle, String contentText, int wakelockid) {
        Intent intent = new Intent(this, IntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_home_logo);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setLargeIcon(bitmap)
                .setSmallIcon(android.R.drawable.bottom_bar)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

//        // 額外通知設定
//        Notification note = notificationBuilder.build();
//        // 通知 震動
//        if (Preference.preferences.getBoolean(Preference.NOTIFICATION_VIBRATE, false)) {
//            note.defaults |= Notification.DEFAULT_VIBRATE;
//        }
//        // 通知 聲音
//        if (Preference.preferences.getString(Preference.NOTIFICATION_SOUND, "").equals("Default")) {
//            note.defaults |= Notification.DEFAULT_SOUND;
//        }
//        // 通知 燈光
//        note.defaults |= Notification.DEFAULT_LIGHTS;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // wakelockid 版本不同 會出現不同 notification，相同則覆蓋。
//        notificationManager.notify(wakelockid /* ID of notification */, note);
        notificationManager.notify(wakelockid /* ID of notification */, notificationBuilder.build());
    }
}
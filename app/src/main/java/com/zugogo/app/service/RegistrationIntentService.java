package com.zugogo.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


public class RegistrationIntentService extends IntentService {
    private static final String TAG = RegistrationIntentService.class.getSimpleName();
    private static final String[] TOPICS = {"global"};
    private static final String SenderID = "361595324240";

    public RegistrationIntentService() {
        super(TAG);
    }

    public static RegistrationIntentService getInstance() {
        return new RegistrationIntentService();
    }

    // 取得 GCM Token
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String token = InstanceID.getInstance(this).getToken(SenderID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.d(TAG, token);
            subscribeTopics(token);
        } catch (Exception e) {
            Log.e(TAG, "Failed to complete token refresh", e);
        }
        Intent registrationComplete = new Intent("registrationComplete");
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            Log.d(TAG, topic);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        /**
         * 送GCM Token 到 Push Server 註冊
         * localhost Push Server
         * */

//        DataOutputStream dos = null;
//        HttpURLConnection con = null;
//        try {
//            URL url = new URL("http://172.16.0.103:8080/GCMServerEx/RegisterServlet");
//            con = (HttpURLConnection) url.openConnection();
//            con.setDoOutput(true);
//            con.setRequestMethod("POST");
//            con.setUseCaches(false);
//            con.connect();
//
//            dos = new DataOutputStream(con.getOutputStream());
//            String content = "regId=" + URLEncoder.encode(token, "UTF-8");
//            Log.i(TAG, "sendRegIdToServer () URL" + content);
//            dos.writeBytes(content);
//            dos.flush();
//
//            int statusCode = con.getResponseCode();
//            if (statusCode == 200) {
//                Log.e(TAG, "send regId to server");
//            } else {
//                throw new Exception();
//            }
//
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        } finally {
//            if (dos != null) {
//                try {
//                    dos.close();
//                } catch (IOException e) {
//                    Log.e(TAG, e.toString());
//                }
//            }
//            if (con != null) {
//                con.disconnect();
//            }
//        }
    }
}

package com.zugogo.app.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by evan on 2018/1/5.
 */

public class ExternalService {
    private static final String TAG = ExternalService.class.getSimpleName();

    public static void intentToGoogleMap(Context context){
        Uri gmmIntentUri = Uri.parse("google.navigation:q=25.0707987,121.5388215");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    public static void intentToEwallet(Context context){
//        Uri gmmIntentUri = Uri.parse("google.navigation:q=25.0707987,121.5388215");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        context.startActivity(mapIntent);
    }
}

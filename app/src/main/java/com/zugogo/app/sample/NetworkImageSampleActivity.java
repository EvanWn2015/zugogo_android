package com.zugogo.app.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.zugogo.app.R;

import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;

public class NetworkImageSampleActivity extends AppCompatActivity {
    private static final String TAG = NetworkImageSampleActivity.class.getSimpleName();
    private Context context;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image_sample);
        context = this;
        img = findViewById(R.id.img);

        Glide.with(context)
                .load("http://cdn.pcwallart.com/images/gif-wallpaper-3.jpg")
                .into(img);
    }

}

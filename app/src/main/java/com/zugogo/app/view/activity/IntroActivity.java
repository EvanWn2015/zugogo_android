package com.zugogo.app.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zugogo.app.R;
import com.zugogo.app.sample.SampleActivity;

public class IntroActivity extends BasisActivity {
    public static final String TAG = IntroActivity.class.getSimpleName();
    private Context context;
    private Button btn_sample;
//    private Button btn_home, btn_sample_list_view, btn_sample_camera, btn_sample_network_image, btn_sample_marquee,
//            btn_get_gcm_token, btn_external_service, btn_scan_rq_code,btn_sample_web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        context = this;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申請 RECORD_AUDIO 權限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1234);
        }

        btn_sample = findViewById(R.id.btn_sample);
        btn_sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SampleActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "QR code : " + data);
        // TODO QR code request
    }


}

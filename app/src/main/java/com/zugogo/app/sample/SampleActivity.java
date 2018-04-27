package com.zugogo.app.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zugogo.app.R;
import com.zugogo.app.view.activity.BasisActivity;
import com.zugogo.app.view.activity.DrawerActivity;
import com.zugogo.app.service.ExternalService;
import com.zugogo.app.service.RegistrationIntentService;
import com.zugogo.app.view.customize.layout.QRcodeCaptureActivity;

public class SampleActivity extends BasisActivity implements View.OnClickListener {
    public static final String TAG = SampleActivity.class.getSimpleName();
    private Context context;

    private Button btn_home, btn_sample_list_view, btn_sample_camera, btn_sample_network_image, btn_sample_marquee,
            btn_get_gcm_token, btn_external_service, btn_scan_rq_code, btn_sample_web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        context = this;

        btn_home = findViewById(R.id.btn_home);
        btn_sample_list_view = findViewById(R.id.btn_sample_list_view);
        btn_sample_camera = findViewById(R.id.btn_sample_camera);
        btn_sample_network_image = findViewById(R.id.btn_sample_network_image);
        btn_sample_marquee = findViewById(R.id.btn_sample_marquee);
        btn_get_gcm_token = findViewById(R.id.btn_get_gcm_token);
        btn_external_service = findViewById(R.id.btn_external_service);
        btn_scan_rq_code = findViewById(R.id.btn_scan_rq_code);
        btn_sample_web_view = findViewById(R.id.btn_sample_web_view);

        btn_home.setOnClickListener(this);
        btn_sample_list_view.setOnClickListener(this);
        btn_sample_camera.setOnClickListener(this);
        btn_sample_network_image.setOnClickListener(this);
        btn_sample_marquee.setOnClickListener(this);
        btn_get_gcm_token.setOnClickListener(this);
        btn_external_service.setOnClickListener(this);
        btn_scan_rq_code.setOnClickListener(this);
        btn_sample_web_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_home:
                intent.setClass(context, DrawerActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_sample_list_view:
                intent.setClass(context, ListViewSampleActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_sample_camera:
                intent.setClass(context, CameraActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_sample_network_image:
                intent.setClass(context, NetworkImageSampleActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_sample_web_view:
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_sample_marquee:
                intent.setClass(context, MarqueeSampleActivity.class);
                context.startActivity(intent);
                break;
            case R.id.btn_get_gcm_token:
                intent.setClass(context, RegistrationIntentService.class);
                context.startService(intent);
                break;
            case R.id.btn_external_service:
                ExternalService.intentToGoogleMap(context);
                break;
            case R.id.btn_scan_rq_code:
                intent.setClass(context, QRcodeCaptureActivity.class);
                startActivityForResult(intent, 1212);
            default:
        }
    }
}

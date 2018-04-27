package com.zugogo.app.view.customize.layout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.google.zxing.Result;
import com.google.zxing.client.android.AnimeViewCallback;
import com.google.zxing.client.android.BaseCaptureActivity;
import com.google.zxing.client.android.ViewfinderView;
import com.zugogo.app.R;

public class QRcodeCaptureActivity extends BaseCaptureActivity implements View.OnClickListener {
    private static final String TAG = QRcodeCaptureActivity.class.getSimpleName();

    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_capture);
        surfaceView = findViewById(R.id.preview_view);
        viewfinderView = findViewById(R.id.viewfinder_view);
        findViewById(R.id.back_btn);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return (surfaceView == null) ? (SurfaceView) findViewById(R.id.preview_view) : surfaceView;
    }

    @Override
    public AnimeViewCallback getViewfinderHolder() {
        return (viewfinderView == null) ? (ViewfinderView) findViewById(R.id.viewfinder_view) : viewfinderView;
    }

    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        Log.i(TAG, "dealDecode ~~~~~ " + rawResult.getText() + " " + barcode + " " + scaleFactor);
        playBeepSoundAndVibrate();
        finish();
//        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
//        对此次扫描结果不满意可以调用
        reScan();
    }

    @Override
    public void onClick(View view) {
        playBeepSoundAndVibrate();
        if (view.getId() == R.id.back_btn) {
            finish();
        }
    }


}

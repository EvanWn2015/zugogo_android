package com.zugogo.app.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zugogo.app.R;
import com.zugogo.app.service.RegistrationIntentService;
import com.zugogo.app.view.customize.layout.LoadingBar;
import com.zugogo.app.util.Tools;

/**
 * Created by evan on 2018/1/6.
 */

public abstract class BasisActivity extends AppCompatActivity implements LocationListener {
    private static final String TAG = BasisActivity.class.getSimpleName();
    //    private Context context;
    private static ConnectivityManager cm;
    private LocationManager locationManager;
    public static LoadingBar LOADING_BAR;

    private Handler handler;

    private static final int CAMERA_CODE = 8765;
    private static final String[] CAMERA = new String[]{Manifest.permission.CAMERA};

    private static final int LOCATION_CODE = 9876;
    private static final String[] LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private static final int IO_STREAM_CODE = 1987;
    private static final String[] IO_STREAM = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        context = this;
        LOADING_BAR = new LoadingBar(this);
        // TODO do get GCM token
        Intent intent = new Intent(BasisActivity.this, RegistrationIntentService.class);
        startService(intent);

        // TODO init Manager
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);


        // TODO 權限相關
        // 相機權限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, CAMERA, CAMERA_CODE);
        }
        // GPS 權限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, LOCATION, LOCATION_CODE);
        } else {
            setLocationListener();
        }

        // 寫入權限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, IO_STREAM, IO_STREAM_CODE);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Log.d(TAG, "Location : " + loc);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO check mobile has net work
        if (!Tools.network.hasNetWork(cm)) {
            // TODO show Dialog
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        switch (requestCode) {
            case CAMERA_CODE:
                break;
            case LOCATION_CODE:
                setLocationListener();
                break;
            case IO_STREAM_CODE:
                break;
            default:

        }
    }

    // TODO 偏好儲存設定 寫入
    public SharedPreferences getEditor() {
        return getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);
    }

    // TODO 偏好儲存設定 讀取
    public SharedPreferences getRead (){
        return getPreferences(Context.MODE_PRIVATE);
    }

    @SuppressLint("MissingPermission")
    private void setLocationListener() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }


    @Override
    public void onLocationChanged(Location loc) {
        if (loc != null) {
            Log.d(TAG, "Latitude: " + loc.getLatitude());
            Log.d(TAG, "Longitude: " + loc.getLongitude());
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG, "onStatusChanged: ");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG, "onProviderEnabled: ");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG, "onProviderDisabled: ");
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}

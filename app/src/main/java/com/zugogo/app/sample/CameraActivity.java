package com.zugogo.app.sample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zugogo.app.R;
import com.zugogo.app.util.Tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CameraActivity extends AppCompatActivity {
    private static final String TAG = CameraActivity.class.getSimpleName();
    private Context context;
    private ImageView img;
    private Button btn;

    private static final int REQUEST_TAKE_PIC = 1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        context = this;
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.img);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (isIntentAvailable(context, intent)) {
                    startActivityForResult(intent, REQUEST_TAKE_PIC);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_PIC:
                if (data != null) {
                    Bitmap bmp = (Bitmap)  data.getExtras().get("data");
                    Glide.with(context)
                            .asBitmap()
                            .load(Tools.bitmaps.bitmap2Bytes(bmp))
                            .into(img);

                    byte[] b64file = Base64.encode(Tools.bitmaps.bitmap2Bytes(bmp), Base64.DEFAULT);
                    String b64str = new String(b64file, StandardCharsets.UTF_8);

                    try {
                        File f = Tools.bitmaps.bitmap2File(bmp, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "upload_image.jpg");
                    } catch (IOException e) {
                        Log.e(TAG, "bitmap to File error : ", e);
                    }
                }
                break;
        }
    }

    public boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

}

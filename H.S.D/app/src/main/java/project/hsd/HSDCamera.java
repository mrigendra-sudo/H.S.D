package project.hsd;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialcamera.MaterialCamera;

import java.io.File;

public class HSDCamera extends AppCompatActivity {
    private final static int CAMERA_RQ = 6969;
    File saveDir = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsdcamera);
        startrecording();
    }

    public void startrecording() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Only use external storage directory if permission is granted, otherwise cache directory is used by default

            saveDir = new File(Environment.getExternalStorageDirectory(), "H.S.D");
            saveDir.mkdirs();
        }
            new MaterialCamera(this)
                    .autoRecordWithDelayMs(5000)
                    .countdownMinutes(90)
                    .saveDir(saveDir)
                    .audioDisabled(true)
                    .videoEncodingBitRate(1024000)
                    .audioEncodingBitRate(50000)
                    .start(CAMERA_RQ);


    }
}

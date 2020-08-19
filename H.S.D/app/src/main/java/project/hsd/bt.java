package project.hsd;

import android.Manifest;


import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;

import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.afollestad.materialcamera.MaterialCamera;

import java.io.File;
public class bt extends AppCompatActivity implements SensorEventListener {
    private HorizontalScrollView m;
    TextView textView;
    File saveDir = null;
    private final static int CAMERA_RQ = 6969;
    public EditText number;
    public EditText message;
    boolean alreadyExecuted = false;
SensorManager sm;
    Sensor proximity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi);


        m = (HorizontalScrollView) findViewById(R.id.antc);
        textView = (TextView)findViewById(R.id.display);

        number = (EditText)findViewById(R.id.smsnumber);
        message = (EditText)findViewById(R.id.smsmessage2);
        m.smoothScrollTo(0,textView.getBottom());

sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }




    public void feature () {

        sendSMS(number.getText().toString(), message.getText().toString());
        File saveFolder = new File(Environment.getExternalStorageDirectory(), "H.S.D");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Only use external storage directory if permission is granted, otherwise cache directory is used by default

            saveDir = new File(Environment.getExternalStorageDirectory(), "H.S.D");
            saveDir.mkdirs();
        }
        new MaterialCamera(this)
                .defaultToFrontFacing(true)
                .autoRecordWithDelayMs(5000)
                .countdownMinutes(90)
                .saveDir(saveDir)
                .audioDisabled(true)
                .videoEncodingBitRate(1024000)
                .audioEncodingBitRate(50000)
                .start(CAMERA_RQ);

    }
    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
       textView.setText(String.valueOf(event.values[0]));
if(!alreadyExecuted){
    feature();
    alreadyExecuted = true;
}

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
























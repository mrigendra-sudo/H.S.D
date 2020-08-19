package project.hsd;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
public class MainActivity extends AppCompatActivity {
    private static final int bt = 1;
    private Button hsdButton;
    private Button standard;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            hsdButton = (Button) findViewById(R.id.next);
            standard = (Button) findViewById(R.id.standard);
        }

        public void next(View view) {
            Intent intent = new Intent(MainActivity.this, bt.class);
            startActivityForResult(intent, bt);
        }

        public void standard(View view) {
            final Intent intent = new Intent(MainActivity.this, HSDCamera.class);
            startActivityForResult(intent,1);


        }



    }




    

























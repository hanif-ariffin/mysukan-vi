package masco.com.mysukan_vi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import masco.com.mysukan_vi.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    finish();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

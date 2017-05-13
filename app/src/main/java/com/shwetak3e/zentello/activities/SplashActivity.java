package com.shwetak3e.zentello.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.BookDeliveryAdapter;
import com.shwetak3e.zentello.user_activity.BookParcelDeliveryActivity;

public class SplashActivity extends AppCompatActivity {

    //Splash Screen Timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                //Start your app main activity
                Intent i;
                i= new Intent(SplashActivity.this, BookParcelDeliveryActivity.class);
                SplashActivity.this.startActivity(i);

                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

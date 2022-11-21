package com.example.mentalhealth.player;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import mentalhealth.R;


public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        /*
            Display the activity for some time and proceed to login activity.
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainWindow = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainWindow);
                finish();
            }
        }, 1000);
    }
}

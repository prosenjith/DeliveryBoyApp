package com.example.deliveryboyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.deliveryboyapp.R;
import com.example.deliveryboyapp.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isUserAlreadyLoggedin()){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }

    private boolean isUserAlreadyLoggedin() {
        SharedPreferences sharedpreferences = getSharedPreferences("login_info",
                Context.MODE_PRIVATE);
        String s=sharedpreferences.getString("logged","");
        if(s.equals("logged")){
            return true;
        }else{
            return false;
        }
    }
}

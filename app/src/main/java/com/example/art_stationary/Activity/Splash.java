package com.example.art_stationary.Activity;


import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.LocaleHelper;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.Locale;

public class Splash extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Gloabal_View.statusbar(Splash.this);
        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceHelper.getInstance(context).getid().isEmpty()){
                    Intent i = new Intent(Splash.this, Select_language.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("comeFrom","splash");
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                }else{
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }

            }
        },3000);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

}
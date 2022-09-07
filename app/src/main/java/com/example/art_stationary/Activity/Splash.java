package com.example.art_stationary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;

public class Splash extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Gloabal_View.statusbar(Splash.this);
        context = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceHelper.getInstance(context).getid().isEmpty()){
                    startActivity(new Intent(Splash.this, Select_language.class));
                    finish();
                }else{
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }

            }
        },3000);
    }
}
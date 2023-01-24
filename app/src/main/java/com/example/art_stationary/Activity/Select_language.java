package com.example.art_stationary.Activity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.LocaleHelper;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.Locale;

public class Select_language extends AppCompatActivity {

     ImageView iconeng;
     ImageView iconurdu;
     TextView textenglishh;
     TextView texturdu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        Gloabal_View.statusbar(Select_language.this);

        iconurdu = findViewById(R.id.iconurdu);
        iconeng = findViewById(R.id.iconeng);
        textenglishh = findViewById(R.id.textenglishh);
        texturdu = findViewById(R.id.texturdu);
        Bundle bundle = getIntent().getExtras();

        textenglishh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(getApplicationContext(),"en");
                PreferenceHelper.getInstance(getApplicationContext()).setLangauage("en");
                iconeng.setVisibility(View.VISIBLE);
                iconurdu.setVisibility(View.GONE);
                Log.d(TAG, "onClick: "+"----"+bundle.getString("comeFrom"));
                if (bundle.getString("comeFrom").equals("Profile")){
                    Intent send = new Intent(Select_language.this, MainActivity.class);
                    startActivity(send);
                    return;
                }else{
                    Intent send = new Intent(Select_language.this, Navigate_dots.class);
                    startActivity(send);
                }
                finish();
            }
        });

        texturdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(getApplicationContext(),"ar");
                PreferenceHelper.getInstance(getApplicationContext()).setLangauage("ar");
                iconurdu.setVisibility(View.VISIBLE);
                iconeng.setVisibility(View.GONE);
                if (bundle.getString("comeFrom").equals("Profile") ){
                    Intent send = new Intent(Select_language.this, MainActivity.class);
                    startActivity(send);
                    return;
                }else{
                    Intent send = new Intent(Select_language.this, Navigate_dots.class);
                    startActivity(send);
                }
                finish();
            }
        });

    }

}
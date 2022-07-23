package com.example.art_stationary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Gloabal_View.statusbar(ForgotPassword.this);


    }
}
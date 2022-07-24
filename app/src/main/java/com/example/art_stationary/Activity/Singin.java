package com.example.art_stationary.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Fragments.HomeFragment;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MultipartBody;

public class Singin extends AppCompatActivity implements ServiceResponse {
    TextView txt_donthaveacc;
    TextView text_forgotpass;
    TextView button_signin;
    EditText et_email;
    EditText et_password;
    ImageView image_back;


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +
                    //"(?=.*[a-z])" +
                    //"(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{4,}" +
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Gloabal_View.statusbar(Singin.this);

        txt_donthaveacc = findViewById(R.id.txt_donthaveacc);
        text_forgotpass = findViewById(R.id.text_forgotpass);
        button_signin = findViewById(R.id.button_signin);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        image_back = findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txt_donthaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noaccintent = new Intent(getApplicationContext(), Signup.class);
                startActivity(noaccintent);
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   confirmInput(view);

            }
        });
        text_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotintent = new Intent(Singin.this, ForgotPassword.class);
                startActivity(forgotintent);
            }
        });
    }

    private boolean validateEmail() {
        String emailInput = et_email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            et_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            et_email.setError("Please enter a valid email address");
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = et_password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError("Field can't be empty");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }
    }
    public void confirmInput(View v) {
        if ( !validateEmail() | !validatePassword()) {
            return;
        }else {
            String emailInput = et_email.getText().toString().trim();
            String passwordInput = et_password.getText().toString().trim();
            getSignin(emailInput,passwordInput);
        }
    }

    private void getSignin(String StrRegEmail, String StrRegpass) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("email",StrRegEmail));
        data.add(MultipartBody.Part.createFormData("password",StrRegpass));
        new RetrofitService(this, ServiceUrls.LOGIN, 2, 1, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
//                    JSONObject jsonArray = jsonObject.getJSONObject("data");
//                    PreferenceHelper.getInstance(this).setusername(jsonArray.optString("fullname"));
//                    PreferenceHelper.getInstance(this).setemail(jsonArray.optString("email"));
//
//                    Toast.makeText(this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
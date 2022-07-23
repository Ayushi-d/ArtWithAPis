package com.example.art_stationary.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements ServiceResponse {

     TextView text_haveacc;
     EditText et_email;
     EditText et_password;
     EditText et_name;
     EditText et_mobile;
     EditText et_confirmpassword;
     Button button_signup;
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
        setContentView(R.layout.activity_signup);
        Gloabal_View.statusbar(Signup.this);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmpassword = findViewById(R.id.et_confirmpassword);
        et_name = findViewById(R.id.et_name);
        et_mobile = findViewById(R.id.et_mobile);
        button_signup = findViewById(R.id.button_signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //add confirm input
                String StrRegEmail = et_email.getText().toString().trim();
                String StrUsername = et_name.getText().toString().trim();
                String Strpassword = et_password.getText().toString().trim();
                String Strconfpassword = et_confirmpassword.getText().toString().trim();
                String Strmobile = et_mobile.getText().toString().trim();

                if (StrRegEmail.equals("")){

                }else if (StrUsername.equals("")){
                    et_name.setError("Please enter your username!");
                }else if (Strpassword.equals("")){
                    et_name.setError("Please enter your password");
                }else if (Strconfpassword.equals("")){
                    et_name.setError("Please enter your confirmed password");
                }else if (Strmobile.equals("")){
                    et_name.setError("Please enter your valid 10 digit mobile number");

                }else {
                    confirmInput(view);
                }
                //
            }
        });
        image_back =findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        text_haveacc = findViewById(R.id.text_haveacc);
        text_haveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Signup.this, Singin.class);
                startActivity(send);
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
        }
        else {
            et_email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = et_name.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            et_name.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            et_name.setError("Username too long");
            return false;
        } else {
            et_name.setError(null);
            return true;
        }
    }
    public boolean validate(){
        String passwordInput = et_password.getText().toString().trim();
        String passwordconfInput = et_confirmpassword.getText().toString().trim();
        boolean temp=true;

        if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_password.setError("Password too weak");
            return false;
        }else if(!passwordInput.equals(passwordconfInput)){
            Toast.makeText(this,"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }

    private boolean validatePassword() {
        String passwordInput = et_password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_password.setError("Password too weak");
            return false;

        }else {
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }else if (validate()){
            String StrRegEmail = et_email.getText().toString().trim();
            String StrUsername = et_name.getText().toString().trim();
            String Strpassword = et_password.getText().toString().trim();
            String Strconfpassword = et_confirmpassword.getText().toString().trim();
            String Strmobile = et_mobile.getText().toString().trim();
            getRegisterNewUser(StrRegEmail,Strpassword,StrUsername,Strmobile,Strconfpassword,"");
        }
    }
    private void getRegisterNewUser(String StrRegEmail, String StrRegpass,String StrFullname,String Strmobile,String StrConfpass,String StrDeviceToken) {
        JsonObject data =new  JsonObject();
        data.addProperty("fullname", StrFullname);
        data.addProperty("email", StrRegEmail);
        data.addProperty("mobile", Strmobile);
        data.addProperty("password", StrRegpass);
        data.addProperty("confpassword",StrConfpass );
        data.addProperty("devicetoken","" );
        data.addProperty("devicetype", "2");
        new RetrofitService(this, ServiceUrls.REGISTER, 2, 1, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        Log.d(TAG, "onServiceResponse: API Response---"+result+requestCode+resCode);

        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d(TAG, "onServiceResponse: API Response---"+result.toString());
                if (jsonObject.has("statusCode") && jsonObject.optInt("statusCode")==200) {
                    JSONObject jsonArray = jsonObject.getJSONObject("data");
                    PreferenceHelper.getInstance(this).setusername(jsonArray.optString("fullname"));
                    PreferenceHelper.getInstance(this).setemail(jsonArray.optString("email"));
                    jsonArray.optString("mobile");
                    jsonArray.optString("devicetoken");
                    jsonArray.optString("devicetype");
                    PreferenceHelper.getInstance(this).setid(jsonObject.optString("_id"));

                    Toast.makeText(this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                    Intent send = new Intent(Signup.this, Singin.class);
                    startActivity(send);
                    }


                } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Response---"+e.toString());

                e.printStackTrace();
            }
        }

    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onServiceResponse: API Error---"+error.toString());


    }
}

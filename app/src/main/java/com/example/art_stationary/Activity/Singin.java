package com.example.art_stationary.Activity;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import org.json.JSONArray;
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
    ImageView image_back,img_google,img_fb,eyeButton;
    int RC_SIGN_IN = 200;
    Boolean passwordVisiblity = false;


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
        img_google = findViewById(R.id.img_google);
        img_fb = findViewById(R.id.img_fb);
        eyeButton = findViewById(R.id.eyeButton);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        eyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordVisiblity == true){
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordVisiblity = false;
                }else{
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordVisiblity = true;
                }
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

        img_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLoginIn();
            }
        });

        img_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogIn();
            }
        });
    }

    private void facebookLogIn(){

    }

    private void googleLoginIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d(TAG, "handleSignInResult: detail"+account.getEmail()+account.getDisplayName());
            socailLogin(account.getDisplayName(),account.getEmail());
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean validateEmail() {
        String emailInput = et_email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            et_email.setError(this.getText(R.string.emptyFields));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            et_email.setError(this.getText(R.string.invalidEmail));
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = et_password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError(this.getText(R.string.emptyFields));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getSignin(String StrRegEmail, String StrRegPass) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("email",StrRegEmail));
        data.add(MultipartBody.Part.createFormData("password",StrRegPass));
        new RetrofitService(this, ServiceUrls.LOGIN, 2, 1, data, this)
                .callService(true);
    }

    private void socailLogin(String name, String email) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("name",name));
        data.add(MultipartBody.Part.createFormData("email",email));
        new RetrofitService(this, ServiceUrls.SOCAILLOGIN, 2, 2, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    int success = output.optInt("success");
                    if (success == 0){
                        Toast.makeText(this, "wrong credentials", Toast.LENGTH_SHORT).show();
                    }else{
                        JSONArray jsonArray =  output.getJSONArray("data");
                        JSONObject data = jsonArray.getJSONObject(0);
                        Log.d(TAG, "onServiceResponse: id---"+data.optString("id"));
                        PreferenceHelper.getInstance(this).setid(data.optString("id"));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            } catch (Exception e) {
                //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
package com.example.art_stationary.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class ForgotPassword extends AppCompatActivity implements ServiceResponse {

    ConstraintLayout toolbar;
    TextView tooltext;
    EditText et_email;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Gloabal_View.statusbar(ForgotPassword.this);
        toolbar =   findViewById(R.id.toolbar);
        et_email =   findViewById(R.id.et_email);
        button_submit =   findViewById(R.id.button_submit);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Forgot Password");

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Stremail= et_email.getText().toString();
                if (Stremail.equals("")){
                    et_email.setError("Please enter registered email");
                }else {
                    getForgotpassword(Stremail);
                }
            }
        });

    }


    private void getForgotpassword(String StrRegEmail) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("email",StrRegEmail));
        new RetrofitService(this, ServiceUrls.FORGOT, 2, 1, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d(TAG, "onServiceResponse: API Response---"+result.toString());
                if (resCode==200) {
//                    JSONObject jsonArray = jsonObject.getJSONObject("data");
//                    PreferenceHelper.getInstance(this).setusername(jsonArray.optString("fullname"));
//                    PreferenceHelper.getInstance(this).setemail(jsonArray.optString("email"));
//                    jsonArray.optString("mobile");
//                    jsonArray.optString("devicetoken");
//                    jsonArray.optString("devicetype");
//                    PreferenceHelper.getInstance(this).setid(jsonObject.optString("_id"));

                    Toast.makeText(this, "Successfully Sent To email", Toast.LENGTH_SHORT).show();
                    /*Intent send = new Intent(ForgotPassword.this, Singin.class);
                    startActivity(send);*/
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
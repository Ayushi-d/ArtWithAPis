package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Activity.MainActivity;
import com.example.art_stationary.Activity.Signup;
import com.example.art_stationary.Activity.Singin;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MultipartBody;

public class ChangePasswordFragment extends Fragment  implements ServiceResponse {
    BottomNavigationView navBar;
    ConstraintLayout toolbar;
    TextView tooltext;
    EditText et_currentpassword;
    EditText et_newpassword;
    EditText et_confirmpassword;
    ConstraintLayout img_back;
    Button button_update;
    ImageView backImg;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_change_password, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));

        et_currentpassword =view.findViewById(R.id.et_currentpassword);
        et_newpassword =view.findViewById(R.id.et_newpassword);
        et_confirmpassword =view.findViewById(R.id.et_confirmpassword);
        button_update =view.findViewById(R.id.button_update);
        img_back =view.findViewById(R.id.img_back);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        backImg = toolbar.findViewById(R.id.backImg);
        tooltext.setText(R.string.changepass);
        if (PreferenceHelper.getInstance(getActivity()).getLangauage()=="ar"){
            backImg.setScaleX(-1);
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StrOldpass= et_currentpassword.getText().toString();
                String StrNewpass= et_newpassword.getText().toString();
                String StrConfpass= et_confirmpassword.getText().toString();
              //  confirmInput(view);
                if (StrOldpass.equals("")){
                    et_currentpassword.setError("Please enter your old password");
                }else if (StrNewpass.equals("")){
                    et_currentpassword.setError("Please enter your New password");
                }else if (StrConfpass.equals("")){
                    et_currentpassword.setError("Please enter your Confirm password");
                }else {
                    String loginID = PreferenceHelper.getInstance(getActivity()).getid();

                    getchangepassword(loginID,StrOldpass,StrNewpass,StrOldpass);
                }
            }
        });

        return view;
    }
    private boolean validatePassword() {
        String passwordInput = et_newpassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_newpassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_newpassword.setError("Password too weak");
            return false;
        }else {
            return true;
        }
    }
    public boolean validate(){
        String passwordInput = et_newpassword.getText().toString().trim();
        String passwordconfInput = et_confirmpassword.getText().toString().trim();
        boolean temp=true;

        if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_newpassword.setError("Password too weak");
            return false;
        }else if(!passwordInput.equals(passwordconfInput)){
            Toast.makeText(getActivity(),"Password Not matching",Toast.LENGTH_SHORT).show();
            temp=false;
        }
        return temp;
    }
    public void confirmInput(View v) {
        if ( !validatePassword()) {
            return;

        }else if (validate()){
            String input = "New Password: " + et_newpassword.getText().toString();
            input += "\n";
            input += "Confirm Password: " + et_confirmpassword.getText().toString();
            input += "\n";
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "Password Changed Sucessfull", Toast.LENGTH_SHORT).show();

        }
    }
    private void getchangepassword(String loginid, String oldpass, String newpass, String confpassword) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("loginid",loginid));
        data.add(MultipartBody.Part.createFormData("oldpassword",oldpass));
        data.add(MultipartBody.Part.createFormData("newpassword",newpass));
        data.add(MultipartBody.Part.createFormData("confpassword",confpassword));
        new RetrofitService(getContext(), ServiceUrls.CHANGEPASSWORD, 2, 1, data, this)
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
//                    PreferenceHelper.getInstance(getContext()).setusername(jsonArray.optString("fullname"));
//                    PreferenceHelper.getInstance(getContext()).setemail(jsonArray.optString("email"));
//                    jsonArray.optString("mobile");
//                    jsonArray.optString("devicetoken");
//                    jsonArray.optString("devicetype");
//                    PreferenceHelper.getInstance(getContext()).setid(jsonObject.optString("_id"));
//
                    Toast.makeText(getContext(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();
//                    Intent send = new Intent(getContext(), Singin.class);
//                    startActivity(send);
                }


            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Response---"+e.toString());

                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onServiceResponse: API Error---"+error.toString());
    }
}
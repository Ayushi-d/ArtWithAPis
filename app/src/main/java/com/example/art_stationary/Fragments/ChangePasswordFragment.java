package com.example.art_stationary.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Activity.MainActivity;
import com.example.art_stationary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Pattern;

public class ChangePasswordFragment extends Fragment {
    BottomNavigationView navBar;
    ConstraintLayout toolbar;
    TextView tooltext;
    EditText et_currentpassword;
    EditText et_newpassword;
    EditText et_confirmpassword;
    ConstraintLayout img_back;
    Button button_update;

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
        tooltext.setText("Change Password");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInput(view);
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
}
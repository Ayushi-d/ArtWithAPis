package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Activity.MainActivity;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class ContactUsFragment extends Fragment implements ServiceResponse {
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    EditText et_name,et_codemobile,et_mobile,et_message;
    Button button_submit;
    ConstraintLayout img_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact_us, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        toolbar = view.findViewById(R.id.toolbar);
        et_name = view.findViewById(R.id.et_name);
        img_back = view.findViewById(R.id.img_back);
        et_codemobile = view.findViewById(R.id.et_codemobile);
        et_mobile = view.findViewById(R.id.et_mobile);
        et_message = view.findViewById(R.id.et_message);
        button_submit = view.findViewById(R.id.button_submit);
        tooltext = view.findViewById(R.id.toolheadtext);
        tooltext.setText("Contact Us");
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitContactUs();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    private void hitContactUs() {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("name",et_name.getText().toString()));
        data.add(MultipartBody.Part.createFormData("mobile",et_codemobile.getText().toString()+et_mobile.getText().toString()));
        data.add(MultipartBody.Part.createFormData("message",et_message.getText().toString()));
        new RetrofitService(getActivity(), ServiceUrls.CONTACTUS, 2, 1, data, this)
                .callService(true);
    }


    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    Toast.makeText(getActivity(), "Contacted Successfully", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
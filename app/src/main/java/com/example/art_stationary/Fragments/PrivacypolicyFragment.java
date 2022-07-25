package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
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

public class PrivacypolicyFragment extends Fragment implements ServiceResponse {

    BottomNavigationView navBar;
    TextView textView;
    ConstraintLayout toolbar;
    TextView tooltext;
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
        View view= inflater.inflate(R.layout.fragment_privacypolicy, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        img_back =view.findViewById(R.id.img_back);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        textView = view.findViewById(R.id.toolheadtext);
        textView.setText("Privacy Policy");

        getprivacypolicy();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void getprivacypolicy() {
        new RetrofitService(getContext(), ServiceUrls.PRIVACYPOLICY,
                1, 1, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d(ContentValues.TAG, "onServiceResponse: API Response---"+result.toString());
                if (resCode==200) {
                    Toast.makeText(getContext(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {
                Log.d(ContentValues.TAG, "onServiceResponse: API Response---"+e.toString());

                e.printStackTrace();
            }
        }

    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }
}
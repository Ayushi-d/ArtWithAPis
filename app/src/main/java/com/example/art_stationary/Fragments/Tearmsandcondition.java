package com.example.art_stationary.Fragments;

import android.content.ContentValues;
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
import android.widget.Toolbar;

import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

public class Tearmsandcondition extends Fragment implements ServiceResponse {
   TextView textView;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    ConstraintLayout img_back;


    public Tearmsandcondition() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }/////..llll9
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_tearmsandcondition, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        img_back =view.findViewById(R.id.img_back);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        textView = view.findViewById(R.id.toolheadtext);
        textView.setText("Tearms and Condition");

        gettearmsandcondition();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return view;

    }

    private void gettearmsandcondition() {
        new RetrofitService(getContext(), ServiceUrls.TEARMSANDCONDITION,
                1, 1, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d(ContentValues.TAG, "onServiceResponse: API Response---"+result.toString());
                if (resCode==200) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
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
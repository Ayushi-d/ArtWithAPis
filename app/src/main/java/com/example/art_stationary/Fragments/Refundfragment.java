package com.example.art_stationary.Fragments;

import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;


public class Refundfragment extends Fragment implements ServiceResponse {
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    TextView textView,contentRefundText;
    ConstraintLayout img_back;


    public Refundfragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_refundfragment, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        img_back =view.findViewById(R.id.img_back);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        toolbar = view.findViewById(R.id.toolbar);
        textView = view.findViewById(R.id.toolheadtext);
        contentRefundText = view.findViewById(R.id.contentRefundText);
        textView.setText("Refund policy");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        getrefundpolicy();
        return view;
    }

    private void getrefundpolicy() {
        new RetrofitService(getContext(), ServiceUrls.REFUNDPOLICY,
                1, 1, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                Log.d(ContentValues.TAG, "onServiceResponse: API Response---"+result.toString());
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    JSONObject data = jsonArray.getJSONObject(0);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        contentRefundText.setText(Html.fromHtml(data.optString("content"), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        contentRefundText.setText(Html.fromHtml(data.optString("content")));
                    }
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
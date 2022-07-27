package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;


public class ProfileFragment extends Fragment implements ServiceResponse {
    TextView texttearmsandcondition;
    TextView textprivacypolicy;
    TextView textrefundpolicy;
    TextView textchangepassword;
    TextView textmyorder;
    TextView textaddress;
    BottomNavigationView navBar;
    TextView textname;

    public ProfileFragment() {
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
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);
        texttearmsandcondition =  view.findViewById(R.id.texttearmsandcondition);
        textprivacypolicy = view.findViewById(R.id.textprivacypolicy);
        textrefundpolicy = view.findViewById(R.id.textrefundpolicy);
        textchangepassword = view.findViewById(R.id.textchangepassword);
        textmyorder = view.findViewById(R.id.textmyorder);
        textaddress = view.findViewById(R.id.textaddress);
        textname = view.findViewById(R.id.textname);


        getProfile("");
        texttearmsandcondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new Tearmsandcondition());

            }
        });
        textaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new AddressFragment());

            }
        });

        textprivacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new PrivacypolicyFragment());

            }
        });

        textrefundpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new Refundfragment());

            }
        });

        textchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new ChangePasswordFragment());

            }
        });

        textmyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new Myorderfragment());

            }
        });

        //loginid

        return view;
    }


    private void getProfile(String name) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("loginid",name));
        new RetrofitService(getContext(), ServiceUrls.MYPROFILE, 2, 1, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    JSONObject data = jsonArray.getJSONObject(0);
                    Log.d(TAG, "onServiceResponse: id---"+data.optString("id"));
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
package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileFragment extends Fragment {
    TextView texttearmsandcondition;
    TextView textprivacypolicy;
    TextView textrefundpolicy;
    TextView textchangepassword;
    TextView textmyorder;
    TextView textaddress;
    BottomNavigationView navBar;

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

        return view;
    }

}
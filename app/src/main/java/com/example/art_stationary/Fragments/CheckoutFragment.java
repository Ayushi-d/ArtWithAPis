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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CheckoutFragment extends Fragment implements AdapterView.OnItemSelectedListener {
     TextView txt_edit;
     ConstraintLayout card_alladdress;
     Button button_paynow;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    Spinner timespinner;
    ConstraintLayout consspinner;
    ConstraintLayout backimg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_checkout, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        txt_edit=view.findViewById(R.id.txt_edit);
        consspinner=view.findViewById(R.id.consspinner);
        card_alladdress=view.findViewById(R.id.card_alladdress);
        button_paynow=view.findViewById(R.id.button_paynow);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Checkout");
        backimg = toolbar.findViewById(R.id.img_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        timespinner = view.findViewById(R.id.timespinner);
        String[] time = { "Evening 4:00 - 5:00 PM", "Evening 6:00 - 7:00 PM",
                "Evening 7:00 - 8:00 PM", "Evening 8:00 - 9:00 PM",
                "Evening 5:00 - 6:00 PM", "Evening 4:30 - 5:50 PM" };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(time));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinner_item,plantsList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        timespinner.setAdapter(spinnerArrayAdapter);
        consspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timespinner.setSelection(1);
            }
        });

        txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new AddressFragment());

            }
        });
        button_paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new ThankyouFragment());

            }
        });
        card_alladdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new SavedAddressFragment());

            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       /* Toast.makeText(getActivity(),
                time[position],
                Toast.LENGTH_LONG)
                .show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
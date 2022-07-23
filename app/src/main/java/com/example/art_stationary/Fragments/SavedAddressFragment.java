package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Ordernoadapter;
import com.example.art_stationary.Adapter.Savedaddressadapter;
import com.example.art_stationary.Model.OrdernoModel;
import com.example.art_stationary.Model.Savedaddressmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SavedAddressFragment extends Fragment {
    TextView txt_editaddrress;
    RecyclerView savedaddresslist;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;


    private ArrayList<Savedaddressmodel> savedaddressmodelArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_address, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        txt_editaddrress=view.findViewById(R.id.txt_editaddrress);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        savedaddresslist=view.findViewById(R.id.savedaddresslist);

        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Saved Addresss");

        savedaddressmodelArrayList=new ArrayList<>();
        savedaddressmodelArrayList.add(new Savedaddressmodel("Abc","243535","3","Come home and away"));
        savedaddressmodelArrayList.add(new Savedaddressmodel("Abc","243535","3","Come home and away"));
        savedaddressmodelArrayList.add(new Savedaddressmodel("Abc","243535","3","Come home and away"));

        Savedaddressadapter savedaddressmodel=new Savedaddressadapter(savedaddressmodelArrayList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        savedaddresslist.setLayoutManager(linearLayoutManager);
        savedaddresslist.setAdapter(savedaddressmodel);

        txt_editaddrress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new AddressFragment());

            }
        });
        return view;
    }
}
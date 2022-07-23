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

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Ordernoadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.OrdernoModel;
import com.example.art_stationary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class OrdernoFragment extends Fragment {
    RecyclerView recyclerorderno;
    private ArrayList<OrdernoModel> ordernoarraylist;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_orderno, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        recyclerorderno=view.findViewById(R.id.recyclerorderno);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Order no. 96435533");
        ordernoarraylist=new ArrayList<>();
        ordernoarraylist.add(new OrdernoModel("Abc","243535","3",R.drawable.custombookimage));
        ordernoarraylist.add(new OrdernoModel("Abc","243535","3",R.drawable.custombookimage));
        ordernoarraylist.add(new OrdernoModel("Abc","243535","3",R.drawable.custombookimage));
        ordernoarraylist.add(new OrdernoModel("Abc","243535","3",R.drawable.custombookimage));
        Ordernoadapter ordernoadapter=new Ordernoadapter(ordernoarraylist,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerorderno.setLayoutManager(linearLayoutManager);
        recyclerorderno.setAdapter(ordernoadapter);
        return view;
    }
}
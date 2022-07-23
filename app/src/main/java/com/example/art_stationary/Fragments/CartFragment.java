package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Verticalhomeadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Verticallistmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CartFragment extends Fragment {
     RecyclerView cartlist;
     Button button_checkout;
     ConstraintLayout toolbar;
     TextView tooltext;
     BottomNavigationView navBar;
     ConstraintLayout img_back;
    SwipeRefreshLayout swipe_list;


    private ArrayList<Cartmodel> cartmodelArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        swipe_list = view.findViewById(R.id.swipe_list);
        cartlist = view.findViewById(R.id.cartlist);
        img_back =view.findViewById(R.id.img_back);
        button_checkout = view.findViewById(R.id.button_checkout);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);
        navBar.setSelectedItemId(R.id.cart);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Cart");
        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new CheckoutFragment());

            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        cartmodelArrayList=new ArrayList<>();

        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));
        cartmodelArrayList.add(new Cartmodel("Abc","243535",R.drawable.custombookimage));

        Mycartadapter mycartadapter=new Mycartadapter(cartmodelArrayList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        cartlist.setLayoutManager(linearLayoutManager);
        mycartadapter.setOnItemClickListener(new Gridhomeadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Gloabal_View.changeFragment(getActivity(), new ItemFragment());

            }
        });
        cartlist.setAdapter(mycartadapter);

        swipe_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_list.setRefreshing(false);
            }
        });

        return view;
    }
}

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
import android.widget.TextView;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Myorderadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Myordermodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Myorderfragment extends Fragment {
    RecyclerView Myorderslist;
    SwipeRefreshLayout swipe_list;
    private ArrayList<Myordermodel> myordermodelArrayList;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
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
        View view= inflater.inflate(R.layout.fragment_myorderfragment, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        swipe_list = getActivity().findViewById(R.id.swipe_list);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        Myorderslist = view.findViewById(R.id.Myorderslist);
        swipe_list = view.findViewById(R.id.swipe_list);
        toolbar = view.findViewById(R.id.toolbar);
        img_back =view.findViewById(R.id.img_back);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("My Orders");
        myordermodelArrayList=new ArrayList<>();

        myordermodelArrayList.add(new Myordermodel("12333","13234","222022","Approved"));
        myordermodelArrayList.add(new Myordermodel("12333","13234","222022","Pending"));
        Myorderadapter myorderadapter=new Myorderadapter(myordermodelArrayList,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        Myorderslist.setLayoutManager(linearLayoutManager);
        myorderadapter.setOnItemClickListener(new Gridhomeadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Gloabal_View.changeFragment(getActivity(), new OrdernoFragment());

            }
        });
        Myorderslist.setAdapter(myorderadapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        swipe_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_list.setRefreshing(false);
            }
        });
        return view;
    }
}
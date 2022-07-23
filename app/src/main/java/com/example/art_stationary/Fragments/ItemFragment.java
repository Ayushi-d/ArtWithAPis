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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Colorhomeadapter;
import com.example.art_stationary.Adapter.Sizeadapter;
import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.Model.Sizemodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ItemFragment extends Fragment {
    ConstraintLayout constraintLayout;
    ImageView img_back;
    RecyclerView listcolor;
    Button button_addtocart;
    private ArrayList<Colormodel> recyclercolorarraylist;
    private ArrayList<Sizemodel> sizeArrayList;
    BottomNavigationView navBar;
    RecyclerView recyclersizrchart;

    public ItemFragment() {
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
        View view= inflater.inflate(R.layout.fragment_item, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        constraintLayout = view.findViewById(R.id.constraintLayout);
        img_back = view.findViewById(R.id.img_back);
        listcolor = view.findViewById(R.id.listcolor);
        recyclersizrchart = view.findViewById(R.id.recyclersizrchart);
        button_addtocart = view.findViewById(R.id.button_addtocart);

        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        button_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gloabal_View.changeFragment(getActivity(), new CartFragment());
                Toast.makeText(getActivity(), "Added to Cart Successfully", Toast.LENGTH_SHORT).show();


            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });

        recyclercolorarraylist = new ArrayList<>();

        // added data to grid array list
        recyclercolorarraylist.add(new Colormodel(R.color.grey));
        recyclercolorarraylist.add(new Colormodel(R.color.grey));
        recyclercolorarraylist.add(new Colormodel(R.color.buttongreen));
        Colorhomeadapter adapter = new Colorhomeadapter(recyclercolorarraylist, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        listcolor.setLayoutManager(layoutManager);
        listcolor.setAdapter(adapter);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);


        //size list
        sizeArrayList=new ArrayList<>();
        sizeArrayList.add(new Sizemodel("M"));
        sizeArrayList.add(new Sizemodel("S"));
        sizeArrayList.add(new Sizemodel("L"));
        sizeArrayList.add(new Sizemodel("XL"));
        Sizeadapter sizeadapter=new Sizeadapter(sizeArrayList,getActivity());

        recyclersizrchart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 4;
                return true;
            }
        });
        recyclersizrchart.setAdapter(sizeadapter);
        return view;
    }
}
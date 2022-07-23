package com.example.art_stationary.Fragments;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.art_stationary.Adapter.BrandAdpter;
import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mostpopularadapter;
import com.example.art_stationary.Adapter.Offeradapter;
import com.example.art_stationary.Adapter.ViewPagerAdapter;
import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Mostpopularmodel;
import com.example.art_stationary.Model.Offermodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    private RecyclerView gridlist;
    private RecyclerView verticallist;
    private RecyclerView Offerslist;
    private RecyclerView mostpopularlist;
    LinearLayout SliderDots;
    private List<ImageView> dots;
    private final static int NUM_PAGES = 3;
    LinearLayout dotsLayout;
    ImageView imagesearch;
    TextView text_viewall;



    private ArrayList<Recyclerhomemodel> recyclerDataArrayList;
    private ArrayList<BrandModel> verticalArraylist;
    private ArrayList<Offermodel> OffersArraylist;
    private ArrayList<Mostpopularmodel> mostpopularArraylist;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);

       // addDots();
        //Global.instance.showhidebottomNav(view,true);

        text_viewall = view.findViewById(R.id.text_viewall);
        imagesearch = view.findViewById(R.id.imagesearch);
        gridlist = view.findViewById(R.id.gridlist);
        verticallist = view.findViewById(R.id.verticallist);
        Offerslist = view.findViewById(R.id.Offerslist);
        mostpopularlist = view.findViewById(R.id.mostpopularlist);
        dotsLayout = view.findViewById(R.id.SliderDots);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // created new array list..
        recyclerDataArrayList = new ArrayList<>();
        verticalArraylist = new ArrayList<>();
        OffersArraylist = new ArrayList<>();
        mostpopularArraylist = new ArrayList<>();

        // added data to grid array list
        recyclerDataArrayList.add(new Recyclerhomemodel("STABILO Swing Cool ", "12.00 KWD", R.drawable.custombookimage));
        recyclerDataArrayList.add(new Recyclerhomemodel("STABILO Swing Cool ", "34.66 KWD", R.drawable.custombookimage));
        recyclerDataArrayList.add(new Recyclerhomemodel("STABILO Swing Cool ", "77.88 KWD", R.drawable.custombookimage));
        recyclerDataArrayList.add(new Recyclerhomemodel("STABILO Swing Cool ", "56.00 KWD", R.drawable.custombookimage));
        Gridhomeadapter adapter = new Gridhomeadapter(recyclerDataArrayList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        gridlist.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new Gridhomeadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Gloabal_View.changeFragment(getActivity(), new ItemFragment());

            }
        });
        gridlist.setAdapter(adapter);

        // added data to Vertical array list
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        verticalArraylist.add(new BrandModel(R.drawable.custombrands));
        BrandAdpter verticaladapter = new BrandAdpter(verticalArraylist, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false);
        verticallist.setLayoutManager(gridLayoutManager);
        verticallist.setAdapter(verticaladapter);

        // added data to Offer array list
        OffersArraylist.add(new Offermodel(R.drawable.offerplaceholder));
        OffersArraylist.add(new Offermodel(R.drawable.custombookimage));
        OffersArraylist.add(new Offermodel(R.drawable.offerplaceholder));
        OffersArraylist.add(new Offermodel(R.drawable.custombookimage));
        Offeradapter offeradapter = new Offeradapter(OffersArraylist, getActivity());
        LinearLayoutManager offermanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        Offerslist.setLayoutManager(offermanager);
        Offerslist.setAdapter(offeradapter);

        // added data to Popular array list
        mostpopularArraylist.add(new Mostpopularmodel("Java", "12.00 KWD", R.drawable.custombookimage));
        mostpopularArraylist.add(new Mostpopularmodel("Java", "12.00 KWD", R.drawable.custombookimage));
        mostpopularArraylist.add(new Mostpopularmodel("Java", "12.00 KWD", R.drawable.custombookimage));
        mostpopularArraylist.add(new Mostpopularmodel("Java", "12.00 KWD", R.drawable.custombookimage));
        Mostpopularadapter mostpopularadapter = new Mostpopularadapter(mostpopularArraylist, getActivity());
        LinearLayoutManager mostpopularmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mostpopularlist.setLayoutManager(mostpopularmanager);
        mostpopularlist.setAdapter(mostpopularadapter);
        addDots();

        text_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new ViewAllFragment());

            }
        });
        imagesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gloabal_View.changeFragment(getActivity(), new SearchFragment());
            }
        });
        return view;
    }

    public void addDots() {
        dots = new ArrayList<>();


        for (int i = 0; i < NUM_PAGES; i++) {
            ImageView dot = new ImageView(getActivity());
            if (i == 0){
                dot.setImageDrawable(getResources().getDrawable(R.drawable.selecteddots));
            }else{
                dot.setImageDrawable(getResources().getDrawable(R.drawable.notselected));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            params.setMargins(8,0,8,0);
            dotsLayout.addView(dot, params);
            dots.add(dot);
        }

    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < NUM_PAGES; i++) {
                int drawableId = (i == idx) ? (R.drawable.selecteddots) : (R.drawable.notselected);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }

    }
}
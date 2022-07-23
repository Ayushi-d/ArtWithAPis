package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Tabcategoriesadapter;
import com.example.art_stationary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class AllcategoriesFragment extends Fragment {
    private static final int NUM_PAGES = 3;
    public static ViewPager2 view_pager;
    public static TabLayout tab_layout;
    private Tabcategoriesadapter pagerAdapter;
    // Arrey of strings FOR TABS TITLES
    private String[] titles = new String[]{"Ink Categories", "Toner Categories", "Type writer"};
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
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
        View view= inflater.inflate(R.layout.fragment_allcategories, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        view_pager = view.findViewById(R.id.view_pager);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Ink Categories");
        backimg = toolbar.findViewById(R.id.img_back);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        tab_layout= view.findViewById(R.id.tab_layout);


        pagerAdapter = new Tabcategoriesadapter(getActivity());
        view_pager.setAdapter(pagerAdapter);


        new TabLayoutMediator(tab_layout, view_pager,(tab, position) -> tab.setText(titles[position])).attach();
        return view;
    }
}
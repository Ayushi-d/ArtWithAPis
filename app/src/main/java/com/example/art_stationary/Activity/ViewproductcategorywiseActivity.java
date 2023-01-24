package com.example.art_stationary.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.art_stationary.Adapter.CategoriesAdapter.MyTablayoutAdapter.MyTablayoutAdapter;
import com.example.art_stationary.Adapter.CategoriesAdapter.ViewproductCategoryAdapter.ViewproductCategoryAdapter;
import com.example.art_stationary.Fragments.SubCategoriesViewFragment.SubCategoriesViewFragment;
import com.example.art_stationary.Model.Categories.Subsubcategory;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViewproductcategorywiseActivity extends AppCompatActivity {
    ArrayList<Subsubcategory> listFromActivity1 = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;
    private SubCategoriesViewFragment subCategoriesViewFragment;
    private Activity activity;
    private int postions = 0;
    TabLayout.Tab tab;
    private MyTablayoutAdapter adapter;
    Timer timer = new Timer(); //Global declaration
    private String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproductcategorywise);
        activity = this;
        listFromActivity1 = this.getIntent().getExtras().getParcelableArrayList("StudentDetails");


        initViews();




    }


    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });
        setDynamicFragmentToTabLayout();

    }

    private void setDynamicFragmentToTabLayout() {
        for (int i = 0; i < listFromActivity1.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(listFromActivity1.get(i).getTitle()));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }

        adapter = new MyTablayoutAdapter(activity, getSupportFragmentManager(), tabLayout.getTabCount(), listFromActivity1);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }




}
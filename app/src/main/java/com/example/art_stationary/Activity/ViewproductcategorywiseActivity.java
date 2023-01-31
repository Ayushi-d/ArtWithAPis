package com.example.art_stationary.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.CategoriesAdapter.MyTablayoutAdapter.MyTablayoutAdapter;
import com.example.art_stationary.Fragments.SubCategoriesViewFragment.SubCategoriesViewFragment;
import com.example.art_stationary.Model.Categories.Subcategory;
import com.example.art_stationary.Model.Categories.Subsubcategory;
import com.example.art_stationary.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;

public class ViewproductcategorywiseActivity extends AppCompatActivity {
    ArrayList<Subsubcategory> listFromActivity1 = new ArrayList<>();
    TabLayout tabLayout;
    ImageView backBtn;
    ViewPager viewPager;
    private SubCategoriesViewFragment subCategoriesViewFragment;
    private Activity activity;
    private int postions = 0;
    TabLayout.Tab tab;
    private MyTablayoutAdapter adapter;
    Timer timer = new Timer(); //Global declaration
    private String value;
    private ArrayList<Subcategory> subcategoryonelist = new ArrayList<>();
    String getvaluecategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproductcategorywise);
        activity = this;

         getvaluecategory = getIntent().getStringExtra("categorycheck");

        if (getvaluecategory.equals("fromoneadapter")) {
            subcategoryonelist = this.getIntent().getExtras().getParcelableArrayList("categoryone");
            initViews();
        } else {
            listFromActivity1 = this.getIntent().getExtras().getParcelableArrayList("StudentDetails");
            postions = this.getIntent().getExtras().getInt("position");
            initViews();

        }
    }






//        if (getIntent().getStringExtra("fromseccategory") != null) {
////          subcategoryone =  this.getIntent().getExtras().getParcelableArrayList("categoryone");
////
////            Toast.makeText(activity,    subcategoryone.size()+"" , Toast.LENGTH_SHORT).show();
////        }else {
////
////        }
////        listFromActivity1 = this.getIntent().getExtras().getParcelableArrayList("StudentDetails");
////        postions = this.getIntent().getExtras().getInt("position");
////        initViews();
//        }


    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        backBtn = (ImageView) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        if (getvaluecategory.equals("fromoneadapter")){
            for (int i = 0; i < subcategoryonelist.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(subcategoryonelist.get(i).getTitle()));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }
            adapter = new MyTablayoutAdapter(activity, getSupportFragmentManager(), tabLayout.getTabCount(),listFromActivity1, subcategoryonelist,getvaluecategory);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(postions);
        }else {
            for (int i = 0; i < listFromActivity1.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(listFromActivity1.get(i).getTitle()));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }
            adapter = new MyTablayoutAdapter(activity, getSupportFragmentManager(), tabLayout.getTabCount(), listFromActivity1,subcategoryonelist, getvaluecategory);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(postions);
        }


    }

}
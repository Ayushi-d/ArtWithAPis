package com.example.art_stationary.Adapter.CategoriesAdapter.MyTablayoutAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.art_stationary.Fragments.SubCategoriesViewFragment.SubCategoriesViewFragment;
import com.example.art_stationary.Model.Categories.Subsubcategory;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.ArrayList;

public class MyTablayoutAdapter extends FragmentStatePagerAdapter {

    private final int totalTabs;
    private final ArrayList<Subsubcategory> arrayList;
    //    private final ArrayList<Subsubcategory> arraylist;
    private Context myContext;




    public MyTablayoutAdapter(Activity viewproductcategorywiseActivity, FragmentManager supportFragmentManager, int totalTabs, ArrayList<Subsubcategory> arrayList) {
        super(supportFragmentManager);
        this.myContext = viewproductcategorywiseActivity;
        this.totalTabs =  totalTabs;
        this.arrayList  = arrayList;
    }



    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", Integer.parseInt(arrayList.get(position).id));
        Fragment frag = SubCategoriesViewFragment.newInstance();
        frag.setArguments(b);
        return frag;




//        switch (position) {
//
//
//            default:
//                Toast.makeText(myContext, "working", Toast.LENGTH_SHORT).show();
//                SubCategoriesViewFragment homeFragment1 = new SubCategoriesViewFragment(value);
//                return homeFragment1;
//
//        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}

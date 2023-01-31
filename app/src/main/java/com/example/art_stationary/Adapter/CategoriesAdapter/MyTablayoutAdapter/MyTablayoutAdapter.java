package com.example.art_stationary.Adapter.CategoriesAdapter.MyTablayoutAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.art_stationary.Fragments.SubCategoriesViewFragment.SubCategoriesViewFragment;
import com.example.art_stationary.Model.Categories.Subcategory;
import com.example.art_stationary.Model.Categories.Subsubcategory;

import java.util.ArrayList;

public class MyTablayoutAdapter extends FragmentStatePagerAdapter {

    private final int totalTabs;
    private final ArrayList<Subsubcategory> arrayList;
    private final ArrayList<Subcategory> arrayListcategoryone;
    private final String getvaluecategory;
    //    private final ArrayList<Subsubcategory> arraylist;
    private Context myContext;




    public MyTablayoutAdapter(Activity viewproductcategorywiseActivity, FragmentManager supportFragmentManager, int totalTabs, ArrayList<Subsubcategory> arrayList, ArrayList<Subcategory>arrayListcategoryone, String getvaluecategory) {
        super(supportFragmentManager);
        this.myContext = viewproductcategorywiseActivity;
        this.totalTabs =  totalTabs;
        this.arrayList  = arrayList;
        this.arrayListcategoryone  = arrayListcategoryone;
        this.getvaluecategory  = getvaluecategory;
    }



    @Override
    public Fragment getItem(int position) {
        if (getvaluecategory.equals("fromoneadapter")){
            Bundle b = new Bundle();
            b.putInt("position", Integer.parseInt(arrayListcategoryone.get(position).id));
            Fragment frag = SubCategoriesViewFragment.newInstance();
            frag.setArguments(b);
            return frag;

        }else {
            Bundle b = new Bundle();
            b.putInt("position", Integer.parseInt(arrayList.get(position).id));
            Fragment frag = SubCategoriesViewFragment.newInstance();
            frag.setArguments(b);
            return frag;
        }


    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}

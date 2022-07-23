package com.example.art_stationary.Adapter;

import com.example.art_stationary.Fragments.Categoiesthree;
import com.example.art_stationary.Fragments.Categoriesone;
import com.example.art_stationary.Fragments.Categoriestwo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Tabcategoriesadapter extends FragmentStateAdapter {

    public Tabcategoriesadapter(FragmentActivity fa) {
        super(fa);
    }


    @Override
    public Fragment createFragment(int pos) {
        switch (pos) {
            case 0:
                return new Categoriesone();
            case 1:
                return new Categoriestwo();
            case 2:
                return new Categoiesthree();
            default:
                throw new RuntimeException("Invalid tab position");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}


   /* @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }


        return view;
    }*/

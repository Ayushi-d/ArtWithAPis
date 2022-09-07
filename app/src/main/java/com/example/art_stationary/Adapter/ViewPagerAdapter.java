package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.art_stationary.Model.BannerModel;
import com.example.art_stationary.R;
import com.squareup.picasso.Picasso;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<BannerModel> bannerAraayList;
    private Integer [] images = {R.drawable.placeholderviewimage,
            R.drawable.placeholderviewimage,R.drawable.placeholderviewimage};

    public ViewPagerAdapter(Context context, ArrayList bannerAraayList) {
        this.context = context;
        this.bannerAraayList = bannerAraayList;
    }

    @Override
    public int getCount() {
        return bannerAraayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_dots, null);
        BannerModel bannerModel = bannerAraayList.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context).load("http://kuwaitgate.com/artbookstore/"+bannerModel.getImgid()).into(imageView);
        //imageView.setImageResource(images[position]);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
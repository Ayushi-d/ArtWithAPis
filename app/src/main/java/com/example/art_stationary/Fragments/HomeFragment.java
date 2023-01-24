package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
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

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.BrandAdpter;
import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mostpopularadapter;
import com.example.art_stationary.Adapter.Offeradapter;
import com.example.art_stationary.Adapter.ViewPagerAdapter;
import com.example.art_stationary.Model.BannerModel;
import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Mostpopularmodel;
import com.example.art_stationary.Model.Offermodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;


public class HomeFragment extends Fragment implements ServiceResponse {
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
    TextView text_viewall,text_viewallBrands,text_viewalloffers,text_viewallmostpopular;
    Gridhomeadapter newArrivaladapter;
    ViewPagerAdapter viewPagerAdapter;
    BrandAdpter brandAdpter;
    Mostpopularadapter mostpopularadapter;
    Offeradapter offeradapter;

    private ArrayList<Recyclerhomemodel> recyclerDataArrayList;
    private ArrayList<BrandModel> verticalArraylist;
    private ArrayList<Mostpopularmodel> OffersArraylist;
    private ArrayList<Mostpopularmodel> mostpopularArraylist;
    private ArrayList<BannerModel> bannerArrayList;

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
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);

       // addDots();
        //Global.instance.showhidebottomNav(view,true);
        text_viewall = view.findViewById(R.id.text_viewall);
        text_viewallBrands = view.findViewById(R.id.text_viewallBrands);
        text_viewalloffers = view.findViewById(R.id.text_viewalloffers);
        text_viewallmostpopular = view.findViewById(R.id.text_viewallmostpopular);
        imagesearch = view.findViewById(R.id.imagesearch);
        gridlist = view.findViewById(R.id.gridlist);
        verticallist = view.findViewById(R.id.verticallist);
        Offerslist = view.findViewById(R.id.Offerslist);
        mostpopularlist = view.findViewById(R.id.mostpopularlist);
        dotsLayout = view.findViewById(R.id.SliderDots);

        recyclerDataArrayList = new ArrayList<>();
        verticalArraylist = new ArrayList<>();
        OffersArraylist = new ArrayList<>();
        mostpopularArraylist = new ArrayList<>();
        bannerArrayList = new ArrayList<>();
        getHomePageData();
        getHomePageOffersProduct();

        viewPagerAdapter = new ViewPagerAdapter(getActivity(),bannerArrayList);
        viewPager.setAdapter(viewPagerAdapter);

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


        newArrivaladapter = new Gridhomeadapter(recyclerDataArrayList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        gridlist.setLayoutManager(layoutManager);
        newArrivaladapter.setOnItemClickListener(new Gridhomeadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", recyclerDataArrayList.get(position).getid());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });
        gridlist.setAdapter(newArrivaladapter);


        brandAdpter = new BrandAdpter(verticalArraylist, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false);
        verticallist.setLayoutManager(gridLayoutManager);
        verticallist.setAdapter(brandAdpter);

        brandAdpter.setOnItemClickListener(new BrandAdpter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("brandid", verticalArraylist.get(position).getid());
                bundle.putString("viewAllID","");
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), viewAllFragment);
            }
        });

        offeradapter = new Offeradapter(OffersArraylist, getActivity());
        LinearLayoutManager offermanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        Offerslist.setLayoutManager(offermanager);
        Offerslist.setAdapter(offeradapter);


        mostpopularadapter = new Mostpopularadapter(mostpopularArraylist, getActivity());
        LinearLayoutManager mostpopularmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mostpopularlist.setLayoutManager(mostpopularmanager);
        mostpopularlist.setAdapter(mostpopularadapter);

        mostpopularadapter.setOnItemClickListener(new Mostpopularadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", mostpopularArraylist.get(position).getid());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });


        offeradapter.setOnItemClickListener(new Offeradapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", OffersArraylist.get(position).getid());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });


        text_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("viewAllID","newArrivals");
                bundle.putString("brandid", "");
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), viewAllFragment);
            }
        });

        text_viewallBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("brandid", "");
                bundle.putString("viewAllID","brands");
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), viewAllFragment);
            }
        });

        text_viewallmostpopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("brandid", "");
                bundle.putString("viewAllID","mostPopular");
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), viewAllFragment);
            }
        });

        text_viewalloffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("brandid", "");
                bundle.putString("viewAllID","offers");
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), viewAllFragment);
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

    private void getHomePageData() {
        new RetrofitService(getContext(), ServiceUrls.HOMEAPI,
                1, 1, this).callService(true);
    }

    private void getHomePageOffersProduct() {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("type","offerprods"));
        new RetrofitService(getContext(), ServiceUrls.GETPRODUCTBYOFFER,
                2, 2,data, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    Log.d(TAG, "onServiceResponse: Outpuut Response---"+output.toString());

                    JSONArray bannerArray =  output.getJSONArray("banners");
                    for (int i = 0; i < bannerArray.length(); i++){
                        JSONObject data = bannerArray.optJSONObject(i);
                        BannerModel bannerModel = new BannerModel();
                        bannerModel.setImgid(data.optString("image"));
                        bannerArrayList.add(bannerModel);
                    }
                    viewPagerAdapter.notifyDataSetChanged();
                    addDots();
                    JSONArray newArrivalsArray =  output.getJSONArray("newarrivals");
                    for (int i = 0; i < newArrivalsArray.length(); i++){
                        JSONObject data = newArrivalsArray.optJSONObject(i);
                        Recyclerhomemodel newArrivalModel = new Recyclerhomemodel();
                        newArrivalModel.setTitle(data.optString("title"));
                        newArrivalModel.setPrice(data.optString("price"));
                        newArrivalModel.setImgid(data.optString("image"));
                        newArrivalModel.setid(data.optString("id"));
                        newArrivalModel.setTittlear(data.optString("titlear"));
                        recyclerDataArrayList.add(newArrivalModel);
                    }
                    newArrivaladapter.notifyDataSetChanged();

                    JSONArray brandsArray =  output.getJSONArray("brands");
                    for (int i = 0; i < brandsArray.length(); i++){
                        JSONObject data = brandsArray.optJSONObject(i);
                        BrandModel brandModel = new BrandModel();
                        brandModel.setImgid(data.optString("image"));
                        brandModel.setid(data.optString("id"));
                        verticalArraylist.add(brandModel);
                    }
                    brandAdpter.notifyDataSetChanged();

                    JSONArray popularArray =  output.getJSONArray("mostpopular");
                    for (int i = 0; i < popularArray.length(); i++){
                        JSONObject data = popularArray.optJSONObject(i);
                        Mostpopularmodel mostpopularmodel = new Mostpopularmodel();
                        mostpopularmodel.setTitle(data.optString("title"));
                        mostpopularmodel.setPrice(data.optString("price"));
                        mostpopularmodel.setImgid(data.optString("image"));
                        mostpopularmodel.setid(data.optString("id"));
                        mostpopularmodel.setTitlear(data.optString("titlear"));
                        mostpopularArraylist.add(mostpopularmodel);
                    }
                    mostpopularadapter.notifyDataSetChanged();

                }


            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        }else {
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray popularArray =  output.getJSONArray("data");
                    for (int i = 0; i < popularArray.length(); i++){
                        JSONObject data = popularArray.optJSONObject(i);
                        Mostpopularmodel mostpopularmodel = new Mostpopularmodel();
                        mostpopularmodel.setTitle(data.optString("title"));
                        mostpopularmodel.setPrice(data.optString("price"));
                        mostpopularmodel.setImgid(data.optString("image"));
                        mostpopularmodel.setid(data.optString("id"));
                        mostpopularmodel.setTitlear(data.optString("titlear"));
                        OffersArraylist.add(mostpopularmodel);
                    }
                    offeradapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    public void addDots() {
        dots = new ArrayList<>();


        for (int i = 0; i < bannerArrayList.size(); i++) {
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
        for (int i = 0; i < bannerArrayList.size(); i++) {
                int drawableId = (i == idx) ? (R.drawable.selecteddots) : (R.drawable.notselected);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }

    }
}
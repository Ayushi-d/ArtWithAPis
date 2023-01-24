package com.example.art_stationary.Activity;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Pageradapter;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Model.IntroModel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.LocaleHelper;
import com.example.art_stationary.Utils.PreferenceHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Navigate_dots extends AppCompatActivity implements ServiceResponse {
    RecyclerView banner_view;
    Button btn_next;
    private ArrayList<IntroModel> recylerIntroList;
    Pageradapter pageradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_dots);
        Gloabal_View.statusbar(Navigate_dots.this);

        banner_view = findViewById(R.id.banner_view);
        btn_next = findViewById(R.id.btn_next);


        getLandingBanners();

        recylerIntroList = new ArrayList<>();
        pageradapter = new Pageradapter(recylerIntroList,this);
        LinearLayoutManager mostpopularmanager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(banner_view);
        banner_view.addItemDecoration(new LinePagerIndicatorDecoration());
        banner_view.setLayoutManager(mostpopularmanager);
        banner_view.setAdapter(pageradapter);

        banner_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                     if (mostpopularmanager.findFirstVisibleItemPosition() ==pageradapter.getItemCount()-1){
//                         btn_next.setText("Get Started");
//                     }else{
//                         btn_next.setText("Next");
//                     }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Navigate_dots.this, Singin.class);
                startActivity(send);
                finish();
//                int position = mostpopularmanager.findFirstVisibleItemPosition();
//                if (position == pageradapter.getItemCount()-1){
//                    Intent send = new Intent(Navigate_dots.this, Singin.class);
//                    startActivity(send);
//                    finish();
//                }else{
//                    banner_view.smoothScrollToPosition(position+1);
//                }
            }
        });

    }

    public void getLandingBanners(){
        new RetrofitService(this, ServiceUrls.GETLANDINGBANNER,
                1, 1, this).callService(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    for (int i =0; i<jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(0);
                        IntroModel introModel = new IntroModel();
                        introModel.setImg(data.optString("image"));
                        recylerIntroList.add(introModel);
                    }
                    pageradapter.notifyDataSetChanged();
                    //Log.d(TAG, "onServiceResponse: data---"+data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {

    }
}
package com.example.art_stationary.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.art_stationary.Adapter.Pageradapter;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Model.IntroModel;
import com.example.art_stationary.R;

import java.util.ArrayList;

public class Navigate_dots extends AppCompatActivity {
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

        recylerIntroList = new ArrayList<>();
        recylerIntroList.add(new IntroModel("Quickly","Donec sodales, ex a vulputate varius, nisi enim maximus ante, a placerat enim sem id.",R.drawable.navigationbookicon));
        recylerIntroList.add(new IntroModel("Safely","Donec sodales, ex a vulputate varius, nisi enim maximus ante, a placerat enim sem id.",R.drawable.navigationbookicon));
        recylerIntroList.add(new IntroModel("Easy","Donec sodales, ex a vulputate varius, nisi enim maximus ante, a placerat enim sem id.",R.drawable.navigationbookicon));
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
                     if (mostpopularmanager.findFirstVisibleItemPosition() ==pageradapter.getItemCount()-1){
                         btn_next.setText("Get Started");
                     }else{
                         btn_next.setText("Next");
                     }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = mostpopularmanager.findFirstVisibleItemPosition();
                if (position == pageradapter.getItemCount()-1){
                    Intent send = new Intent(Navigate_dots.this, Singin.class);
                    startActivity(send);
                    finish();
                }else{
                    banner_view.smoothScrollToPosition(position+1);
                }
            }
        });

    }


}
package com.example.art_stationary.Fragments.CategoriesFragment.Presenter;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Adapter.CategoriesRecylerAdapter;
import com.example.art_stationary.Model.Categories.Example;
import com.example.art_stationary.Retrofit.WebServices;
import com.example.art_stationary.Utils.Handler.GetCategoriesHandler;

public class CategoriesPresenter {

    private final FragmentActivity activity;
    private RecyclerView categoriesrecyclerview;
    CategoriesRecylerAdapter categoriesRecylerAdapter;
    WebServices webServices = new WebServices();


    public CategoriesPresenter(FragmentActivity activity, RecyclerView categoriesrecyclerview) {
        this.activity = activity;
        this.categoriesrecyclerview = categoriesrecyclerview;
    }

    public void getcategories() {

        WebServices.getmInstance().getcategories(new GetCategoriesHandler() {

            @Override
            public void onSuccess(Example example) {
                categoriesRecylerAdapter= new CategoriesRecylerAdapter(activity,example.getOutput().getData());
                categoriesrecyclerview.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
                categoriesrecyclerview.setAdapter(categoriesRecylerAdapter);

            }

            @Override
            public void onError(String message) {

            }
        });
    }
}




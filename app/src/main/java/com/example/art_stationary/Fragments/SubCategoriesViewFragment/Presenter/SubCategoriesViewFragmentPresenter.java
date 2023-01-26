package com.example.art_stationary.Fragments.SubCategoriesViewFragment.Presenter;

import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Adapter.CategoriesAdapter.ViewproductCategoryAdapter.ViewproductCategoryAdapter;
import com.example.art_stationary.Model.Categories.Example;
import com.example.art_stationary.Model.GetproductbyidModel.GetproductbyidExample;
import com.example.art_stationary.Retrofit.WebServices;
import com.example.art_stationary.Utils.Handler.GetCategoriesHandler;
import com.example.art_stationary.Utils.Handler.GetproductByidHandler;

import okhttp3.MultipartBody;

public class SubCategoriesViewFragmentPresenter {
    private final RecyclerView viewproductRecylerView;
    WebServices webServices = new WebServices();
    private ViewproductCategoryAdapter viewproductCategoryAdapter;
    MultipartBody.Part idvalue;

    private final FragmentActivity activity;

    public SubCategoriesViewFragmentPresenter(FragmentActivity activity, RecyclerView viewproductRecylerView) {
        this.activity = activity;
        this.viewproductRecylerView = viewproductRecylerView;
    }

    public void getcategories(String id) {
        idvalue = MultipartBody.Part.createFormData("subcatid",id);

        WebServices.getmInstance().getproductbyid(idvalue,new GetproductByidHandler() {

            @Override
            public void onSuccess(GetproductbyidExample example) {

                if (example.getOutput().getSuccess() == 0){
                    Toast.makeText(activity, "No Product Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                viewproductCategoryAdapter = new ViewproductCategoryAdapter(activity,example.getOutput().getData());
                LinearLayoutManager linearLayoutManager = new GridLayoutManager(activity, 2);
                viewproductRecylerView.setLayoutManager(linearLayoutManager);
                viewproductRecylerView.setAdapter(viewproductCategoryAdapter);

            }

            @Override
            public void onError(String message) {

            }


        });
    }
}

package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.art_stationary.Adapter.Categorioneadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Myorderadapter;
import com.example.art_stationary.Model.CategoriesModel;
import com.example.art_stationary.Model.Mycategorimodel;
import com.example.art_stationary.Model.Myordermodel;
import com.example.art_stationary.R;

import java.util.ArrayList;


public class Categoriesone extends Fragment {
    RecyclerView categoriesonelist;
    private ArrayList<CategoriesModel> mycategorimodelArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categoriesone, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        categoriesonelist=view.findViewById(R.id.categoriesonelist);
        mycategorimodelArrayList=new ArrayList<>();

        mycategorimodelArrayList.add(new CategoriesModel("Stabilo swing cool","101550 K",R.drawable.custombookimage));
        mycategorimodelArrayList.add(new CategoriesModel("12333","1234",R.drawable.custombookimage));
        mycategorimodelArrayList.add(new CategoriesModel("12333","1234",R.drawable.custombookimage));
        mycategorimodelArrayList.add(new CategoriesModel("12333","1234",R.drawable.custombookimage));

        Categorioneadapter categorioneadapter=new Categorioneadapter(mycategorimodelArrayList,getActivity());
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        categoriesonelist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        categoriesonelist.setAdapter(categorioneadapter);
        return view;
    }
}
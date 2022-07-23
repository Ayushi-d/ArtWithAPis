package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.CategoriesModel;
import com.example.art_stationary.Model.Mycategorimodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Categorioneadapter extends RecyclerView.Adapter<Categorioneadapter.RecyclerViewHolder> {

    private ArrayList<CategoriesModel> mycategorimodelArrayList;
    private Context mcontext;

    public Categorioneadapter(ArrayList<CategoriesModel> mycategorimodelArrayList, Context mcontext) {
        this.mycategorimodelArrayList = mycategorimodelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcategorione, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        CategoriesModel mycategorimodel = mycategorimodelArrayList.get(position);
        holder.img_book.setImageResource(mycategorimodel.getImgid());
        holder.tv_bookname.setText(mycategorimodel.getTitle());
        holder.tv_pricebook.setText(mycategorimodel.getPrice());

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return mycategorimodelArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bookname,tv_pricebook;
        private ImageView img_book;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_book = itemView.findViewById(R.id.img_book);
        }
    }
}

package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Verticallistmodel;
import com.example.art_stationary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BrandAdpter extends RecyclerView.Adapter<BrandAdpter.RecyclerViewHolder> {

    private ArrayList<BrandModel> DataArrayList;
    private Context mcontext;
    private static ClickListener mOnClickListener;

    public BrandAdpter(ArrayList<BrandModel> DataArrayList, Context mcontext) {
        this.DataArrayList = DataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brandlistlayout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        BrandModel recyclerData = DataArrayList.get(position);
        Picasso.with(mcontext).load("http://kuwaitgate.com/artbookstore/"+recyclerData.getImgid()).into(holder.imagesbook);
        holder.imagesbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return DataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagesbook;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesbook = itemView.findViewById(R.id.brandImages);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }
}

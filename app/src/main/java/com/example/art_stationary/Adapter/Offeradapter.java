package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.art_stationary.Model.Offermodel;
import com.example.art_stationary.Model.Verticallistmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Offeradapter extends RecyclerView.Adapter<Offeradapter.RecyclerViewHolder> {

    private ArrayList<Offermodel> offerArrayList;
    private Context mcontext;

    public Offeradapter(ArrayList<Offermodel> offerArrayList, Context mcontext) {
        this.offerArrayList = offerArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verticalimagecustomlist, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Offermodel recyclerData = offerArrayList.get(position);
        holder.imagesbook.setImageResource(recyclerData.getImgid());
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return offerArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagesbook;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imagesbook = itemView.findViewById(R.id.imagesbook);
        }
    }
}

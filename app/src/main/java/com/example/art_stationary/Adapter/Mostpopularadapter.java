package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Mostpopularmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mostpopularadapter extends RecyclerView.Adapter<Mostpopularadapter.RecyclerViewHolder> {

    private ArrayList<Mostpopularmodel> popularArrayList;
    private Context mcontext;

    public Mostpopularadapter(ArrayList<Mostpopularmodel> popularArrayList, Context mcontext) {
        this.popularArrayList = popularArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridhomelist, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Mostpopularmodel recyclerData = popularArrayList.get(position);
        holder.img_book.setImageResource(recyclerData.getImgid());
        holder.tv_bookname.setText(recyclerData.getTitle());
        holder.tv_pricebook.setText(recyclerData.getPrice());
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return popularArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bookname,tv_pricebook;
        private ImageView img_book;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_book = itemView.findViewById(R.id.img_book);        }
    }
}

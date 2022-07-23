package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Model.IntroModel;
import com.example.art_stationary.Model.Mostpopularmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

public class Pageradapter  extends RecyclerView.Adapter<Pageradapter.RecyclerViewHolder> {

    private ArrayList<IntroModel> popularArrayList;
    private Context mcontext;

    public Pageradapter(ArrayList<IntroModel> popularArrayList, Context mcontext) {
        this.popularArrayList = popularArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public Pageradapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteminto, parent, false);
        return new Pageradapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pageradapter.RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        IntroModel recyclerData = popularArrayList.get(position);
        holder.img_book.setImageResource(recyclerData.getImg());
        holder.tv_bookname.setText(recyclerData.getTitle());
        holder.tv_pricebook.setText(recyclerData.getDescription());
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
            tv_pricebook = itemView.findViewById(R.id.nav_subheadingtext);
            tv_bookname = itemView.findViewById(R.id.nav_headingtext);
            img_book = itemView.findViewById(R.id.imagebookicon);        }
    }
}

package com.example.art_stationary.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Colorhomeadapter extends RecyclerView.Adapter<Colorhomeadapter.RecyclerViewHolder> {

    private ArrayList<Colormodel> Colorarray;
    private Context mcontext;

    public Colorhomeadapter(ArrayList<Colormodel> Colorarray, Context mcontext) {
        this.Colorarray = Colorarray;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcolor, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Colormodel colormodel = Colorarray.get(position);
        Drawable drawable = mcontext.getResources().getDrawable(R.drawable.round);
        holder.coloroval.setBackground(drawable);

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return Colorarray.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View coloroval;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            coloroval = itemView.findViewById(R.id.coloroval);

        }
    }
}

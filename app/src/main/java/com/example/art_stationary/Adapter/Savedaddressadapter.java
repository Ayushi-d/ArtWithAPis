package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.CategoriesModel;
import com.example.art_stationary.Model.Savedaddressmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Savedaddressadapter extends RecyclerView.Adapter<Savedaddressadapter.RecyclerViewHolder> {

    private ArrayList<Savedaddressmodel> savedaddressadapters;
    private Context mcontext;

    public Savedaddressadapter(ArrayList<Savedaddressmodel> savedaddressadapters, Context mcontext) {
        this.savedaddressadapters = savedaddressadapters;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customsavedaddress, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Savedaddressmodel savedaddressmodel = savedaddressadapters.get(position);
        holder.text_delivery.setText(savedaddressmodel.getTextdelivery());
        holder.text_place.setText(savedaddressmodel.getTextplace());
        holder.text_addrress.setText(savedaddressmodel.getTextaddress());
        holder.text_directionwhere.setText(savedaddressmodel.getTextdirection());

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return savedaddressadapters.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView text_delivery,text_place,text_addrress,text_directionwhere;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text_delivery = itemView.findViewById(R.id.text_delivery);
            text_place = itemView.findViewById(R.id.text_place);
            text_addrress = itemView.findViewById(R.id.text_addrress);
            text_directionwhere = itemView.findViewById(R.id.text_directionwhere);
        }
    }
}

package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.CategoriesModel;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.RecyclerViewHolder> {

    private ArrayList<Searchmodel> mysearcharraylist;
    private Context mcontext;

    public Searchadapter(ArrayList<Searchmodel> mysearcharraylist, Context mcontext) {
        this.mysearcharraylist = mysearcharraylist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customsearch, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Searchmodel searchmodel = mysearcharraylist.get(position);
        holder.img_item.setImageResource(searchmodel.getImgid());
        holder.tv_bookname.setText(searchmodel.getTitle());
        holder.tv_pricebook.setText(searchmodel.getPrice());

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return mysearcharraylist.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bookname,tv_pricebook;
        private ImageView img_item;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_item = itemView.findViewById(R.id.img_item);
        }
    }
}

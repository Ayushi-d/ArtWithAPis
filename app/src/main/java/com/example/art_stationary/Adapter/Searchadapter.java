package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.CategoriesModel;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.RecyclerViewHolder> {

    private ArrayList<Searchmodel> mysearcharraylist;
    private Context mcontext;
    private static ClickListener mOnClickListener;
    private static ClickListener mOnCartClickListener;


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
        Picasso.with(mcontext).load("http://kuwaitgate.com/artbookstore/"+searchmodel.getImgid()).into(holder.img_item);
        holder.tv_bookname.setText(searchmodel.getTitle());
        holder.tv_pricebook.setText(searchmodel.getPrice());
        holder.cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCartClickListener.onItemClick(position,v);
            }
        });

        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(position,v);
            }
        });

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
        private Button cardButton;
        private ConstraintLayout mainView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_item = itemView.findViewById(R.id.img_item);
            cardButton = itemView.findViewById(R.id.cardButton);
            mainView = itemView.findViewById(R.id.mainView);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }

    public void setOnCartClickListener(ClickListener clickListener){
        this.mOnCartClickListener = clickListener;
    }
}

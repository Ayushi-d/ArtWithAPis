package com.example.art_stationary.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Viewallmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewalladapter extends RecyclerView.Adapter<Viewalladapter.RecyclerViewHolder>{

    private ArrayList<Viewallmodel> viewallarraylist;
    private Context mcontext;
    private static ClickListener mOnClickListener;

    public Viewalladapter(ArrayList<Viewallmodel> viewallarraylist, Context mcontext) {
        this.viewallarraylist = viewallarraylist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customviewall, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the data to textview and imageview.
        Viewallmodel viewallmodel = viewallarraylist.get(position);
        holder.img_book.setImageResource(viewallmodel.getImgid());
        holder.tv_bookname.setText(viewallmodel.getTitle());
        holder.tv_pricebook.setText(viewallmodel.getPrice());
        holder.img_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(position,v);
            }
        });
    }



    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return viewallarraylist.size();
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

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }




}

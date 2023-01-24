package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.RecyclerViewHolder> {

    private ArrayList<Recyclerhomemodel> wishListArrayList;
    private Context mcontext;
    private static ClickListener mOnClickListener;
    private static ClickListener mOnImageClickListener;

    public WishListAdapter(ArrayList<Recyclerhomemodel> wishListArrayList, Context mcontext) {
        this.wishListArrayList = wishListArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridhomelist, parent, false);
        return new WishListAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Recyclerhomemodel recyclerhomemodel = wishListArrayList.get(position);
        Picasso.with(mcontext).load("http://kuwaitgate.com/artbookstore/"+recyclerhomemodel.getImgid()).into(holder.img_book);
        holder.tv_bookname.setText(recyclerhomemodel.getTitle());
        holder.tv_pricebook.setText(recyclerhomemodel.getPrice());
        holder.likeButton.setVisibility(View.VISIBLE);
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(position,v);
            }
        });
        holder.img_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnImageClickListener.onItemClick(position,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishListArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bookname,tv_pricebook;
        private ImageView img_book,likeButton;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_book = itemView.findViewById(R.id.img_book);
            likeButton = itemView.findViewById(R.id.likeButton);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }

    public void setOnClickListener(ClickListener clickListener) {
        this.mOnImageClickListener = clickListener;
    }



}

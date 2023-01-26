package com.example.art_stationary.Adapter.CategoriesAdapter.ViewproductCategoryAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Activity.ViewproductcategorywiseActivity;
import com.example.art_stationary.Model.GetproductbyidModel.Datum;
import com.example.art_stationary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewproductCategoryAdapter extends RecyclerView.Adapter<ViewproductCategoryAdapter.RecyclerViewHolder> {


    private final FragmentActivity activity;
    private List<Datum> data = new ArrayList<>();

    public ViewproductCategoryAdapter(FragmentActivity viewproductcategorywiseActivity, List<Datum> data) {
        this.activity = viewproductcategorywiseActivity;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewproductcategorywiserecyleritem, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.with(activity).load("http://kuwaitgate.com/artbookstore/"+data.get(position).getImage()).into(holder.img_book);
        holder.tv_bookname.setText(data.get(position).getTitle());
        holder.tv_pricebook.setText(data.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return data.size();
    }


    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_bookname, tv_pricebook;
        private ImageView img_book;
        private CardView card;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pricebook = itemView.findViewById(R.id.tv_pricebook);
            tv_bookname = itemView.findViewById(R.id.tv_bookname);
            img_book = itemView.findViewById(R.id.img_book);
            card = itemView.findViewById(R.id.card);
        }
    }
}

package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mycartadapter extends RecyclerView.Adapter<Mycartadapter.RecyclerViewHolder> {

    private ArrayList<Cartmodel> cartmodelArrayList;
    private Context mcontext;
    private static Gridhomeadapter.ClickListener mOnClickListener;

    public Mycartadapter(ArrayList<Cartmodel> cartmodelArrayList, Context mcontext) {
        this.cartmodelArrayList = cartmodelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custommycard, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Cartmodel recyclerData = cartmodelArrayList.get(position);
        holder.img_item.setImageResource(recyclerData.getImgid());
        holder.textprice.setText(recyclerData.getTitle());
        holder.textdescription.setText(recyclerData.getPrice());    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return cartmodelArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textdescription,textprice,textcount;
        private ImageView img_item,img_plus,img_minus,img_remove;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textdescription = itemView.findViewById(R.id.textdescription);
            textprice = itemView.findViewById(R.id.textprice);
            textcount = itemView.findViewById(R.id.textcount);
            img_item = itemView.findViewById(R.id.img_item);
            img_plus = itemView.findViewById(R.id.img_plus);
            img_minus = itemView.findViewById(R.id.img_minus);
            img_remove = itemView.findViewById(R.id.img_remove);
        }
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(Gridhomeadapter.ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }

}

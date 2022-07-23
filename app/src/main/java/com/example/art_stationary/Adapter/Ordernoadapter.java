package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.OrdernoModel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Ordernoadapter extends RecyclerView.Adapter<Ordernoadapter.RecyclerViewHolder> {

    private ArrayList<OrdernoModel> ordernoModelArrayList;
    private Context mcontext;
    private static Gridhomeadapter.ClickListener mOnClickListener;


    public Ordernoadapter(ArrayList<OrdernoModel> ordernoModelArrayList, Context mcontext) {
        this.ordernoModelArrayList = ordernoModelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custommyorders, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        OrdernoModel ordernoModel = ordernoModelArrayList.get(position);
        holder.img_item.setImageResource(ordernoModel.getImgid());
        holder.textprice.setText(ordernoModel.getPrice());
        holder.textdescription.setText(ordernoModel.getDescription());
        holder.textquantityno.setText(ordernoModel.getQuantity());
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return ordernoModelArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textdescription,textprice,textquantityno;
        private ImageView img_item;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textdescription = itemView.findViewById(R.id.textdescription);
            textprice = itemView.findViewById(R.id.textprice);
            textquantityno = itemView.findViewById(R.id.textquantityno);
            img_item = itemView.findViewById(R.id.img_item);
        }
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(Gridhomeadapter.ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }
}

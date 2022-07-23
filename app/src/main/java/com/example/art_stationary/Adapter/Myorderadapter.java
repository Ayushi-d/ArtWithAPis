package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Myordermodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Myorderadapter extends RecyclerView.Adapter<Myorderadapter.RecyclerViewHolder> {

    private ArrayList<Myordermodel> myordermodelArrayList;
    private Context mcontext;
    private static Gridhomeadapter.ClickListener mOnClickListener;


    public Myorderadapter(ArrayList<Myordermodel> myordermodelArrayList, Context mcontext) {
        this.myordermodelArrayList = myordermodelArrayList;
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
        Myordermodel myordermodel = myordermodelArrayList.get(position);
        holder.tv_ordernum.setText(myordermodel.getOrdernum());
        holder.tv_amountnum.setText(myordermodel.getAmmountno());
        holder.tv_date.setText(myordermodel.getDate());
        holder.tv_statusconfirm.setText(myordermodel.getStatus());    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return myordermodelArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_ordernum,tv_amountnum,tv_date,tv_statusconfirm;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ordernum = itemView.findViewById(R.id.textordernum);
            tv_amountnum = itemView.findViewById(R.id.tv_amountnum);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_statusconfirm = itemView.findViewById(R.id.tv_statusconfirm);
        }
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(Gridhomeadapter.ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }
}

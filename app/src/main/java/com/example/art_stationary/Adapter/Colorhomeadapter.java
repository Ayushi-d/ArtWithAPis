package com.example.art_stationary.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.Model.CombinationModel;
import com.example.art_stationary.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Colorhomeadapter extends RecyclerView.Adapter<Colorhomeadapter.RecyclerViewHolder> {

    private ArrayList<CombinationModel> Colorarray;
    private Context mcontext;
    private static ClickListener mOnClickListener;
    //int row_index=0;

    public Colorhomeadapter(ArrayList<CombinationModel> Colorarray, Context mcontext) {
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
        CombinationModel colormodel = Colorarray.get(position);
        holder.coloroval.setCardBackgroundColor(Color.parseColor(colormodel.getColorCode()));

        if (colormodel.getSelectedColor()){
            holder.coloroval.setStrokeWidth(14);
            holder.coloroval.setStrokeColor(Color.GRAY);
        }else {
            holder.coloroval.setStrokeWidth(0);
            holder.coloroval.setStrokeColor(Color.TRANSPARENT);
        }

        holder.coloroval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(position,v);
//                row_index=position;
//                notifyDataSetChanged();
            }
        });

//        if(row_index==position){
//            holder.coloroval.setStrokeWidth(10);
//            holder.coloroval.setStrokeColor(Color.GRAY);
//        }
//        else
//        {
//            holder.coloroval.setStrokeWidth(0);
//            holder.coloroval.setStrokeColor(Color.TRANSPARENT);
//        }
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return Colorarray.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView coloroval;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            coloroval = itemView.findViewById(R.id.coloroval);

        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }


}

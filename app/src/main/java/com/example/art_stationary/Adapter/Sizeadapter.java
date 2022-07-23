package com.example.art_stationary.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.Model.IntroModel;
import com.example.art_stationary.Model.Sizemodel;
import com.example.art_stationary.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Sizeadapter extends RecyclerView.Adapter<Sizeadapter.RecyclerViewHolder> {

    private ArrayList<Sizemodel> sizeArrayList;
    private Context mcontext;
    int row_index=0;

    public Sizeadapter(ArrayList<Sizemodel> sizeArrayList, Context mcontext) {
        this.sizeArrayList = sizeArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customsize, parent, false);
        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        Sizemodel sizemodel = sizeArrayList.get(position);
        holder.textmedium.setText(sizemodel.getSize());
        holder.backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            Drawable drawable = mcontext.getResources().getDrawable(R.drawable.sizebg);

            holder.backview.setBackground(drawable);
            holder.textmedium.setTextColor(R.color.white);
        }
        else
        {
            holder.backview.setBackgroundColor(Color.parseColor("#EEEEEE"));
            holder.textmedium.setTextColor(R.color.black);

        }

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return sizeArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textmedium;
        private LinearLayout backview;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textmedium = itemView.findViewById(R.id.textmedium);
            backview = itemView.findViewById(R.id.backview);
        }
    }
}

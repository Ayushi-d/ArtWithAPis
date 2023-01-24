package com.example.art_stationary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mycartadapter extends RecyclerView.Adapter<Mycartadapter.RecyclerViewHolder> {

    private ArrayList<Cartmodel> cartmodelArrayList;
    private Context mcontext;
    private static ClickListener mOnClickListener;
    private static ClickListener mOnDeleteClickListener;
    private static ClickListener mOnPlusClickListener;
    private static ClickListener mOnMinusClickListener;
    ArrayList<String> imglist = new ArrayList<>();

    public Mycartadapter(ArrayList<Cartmodel> cartmodelArrayList, ArrayList<String> imglist, Context mcontext) {
        this.cartmodelArrayList = cartmodelArrayList;
        this.mcontext = mcontext;
        this.imglist = imglist;
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
        Picasso.with(mcontext).load("http://kuwaitgate.com/artbookstore/"+imglist.get(position)).into(holder.img_item);
        double ItemPrice = Double.parseDouble(recyclerData.getPrice()) * Double.parseDouble(recyclerData.getQuantity()+".000");
        holder.textprice.setText(ItemPrice+"");
        if (PreferenceHelper.getInstance(mcontext).getLangauage().equals("ar")){
            holder.textdescription.setText(recyclerData.getProdname());
        }else{
            holder.textdescription.setText(recyclerData.getProdname());
        }
        holder.textcount.setText(recyclerData.getQuantity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mOnClickListener.onItemClick(position,v);
            }
        });

        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeleteClickListener.onItemClick(position,v);
            }
        });

        holder.img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count = Integer.parseInt(holder.textcount.getText().toString())+1;
//                holder.textcount.setText(""+count);
                mOnPlusClickListener.onItemClick(position,v);
            }
        });

        holder.img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.textcount.getText().toString()) > 1){
//                    int count = Integer.parseInt(holder.textcount.getText().toString())-1;
//                    holder.textcount.setText(""+count);
                    mOnMinusClickListener.onItemClick(position,v);
                }
            }
        });
    }

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

    public void setOnItemClickListener(ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }

    public void setOnDeleteItemClickListener(ClickListener clickListener) {
        this.mOnDeleteClickListener = clickListener;
    }

    public void setOnPlusClickListener(ClickListener clickListener) {
        this.mOnPlusClickListener = clickListener;
    }

    public void setmOnMinusClickListener(ClickListener clickListener) {
        this.mOnMinusClickListener = clickListener;
    }

}

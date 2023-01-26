package com.example.art_stationary.Adapter;

import android.graphics.Color;
import android.media.Image;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Adapter.CategoriesAdapter.SubCategoriesRecylerAadapter1;
import com.example.art_stationary.Model.Categories.Datum;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriesRecylerAdapter extends RecyclerView.Adapter<CategoriesRecylerAdapter.ViewHolder> {
    private FragmentActivity activity;
    private List<Datum> data;
    private SubCategoriesRecylerAadapter1 subCategoriesRecylerAadapter1;
    private Boolean vallue = true;

    public CategoriesRecylerAdapter(FragmentActivity activity, List<Datum> data) {
        this.activity = activity;
        this.data = data;

    }

    // RecyclerView recyclerView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.categorieslist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        //#9BFFC5
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textView.setText(data.get(position).getTitle());
//        holder.imageView.setImageResource(listdata[position].getImgId());

        String checkingvalue = PreferenceHelper.getInstance(activity).getLangauage();

       // holder.parentView.setBackgroundColor( position % 2 == 0 ? 0xff9BFFC5 : 0xffFFFFFF);
        holder.parentView.setCardBackgroundColor(position % 2 == 0 ? 0xff9BFFC5 : 0xffFFFFFF);

        if (checkingvalue.equals("ar")){
            holder.textView.setText(data.get(position).getTitlear());
        }else {
            holder.textView.setText(data.get(position).getTitle());

        }



        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vallue) {
                    holder.subcategoriesrecyclerview1.setVisibility(View.VISIBLE);
                    //holder.imgarrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_245);
                } else {
                    holder.subcategoriesrecyclerview1.setVisibility(View.GONE);
                   // holder.imgarrow.setImageResource(R.drawable.dropdownarrow);
                }
                vallue = !vallue;
            }
        });


//        holder.imgarrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (vallue) {
//                    holder.subcategoriesrecyclerview1.setVisibility(View.VISIBLE);
//                    holder.imgarrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_245);
//                } else {
//                    holder.subcategoriesrecyclerview1.setVisibility(View.GONE);
//                    holder.imgarrow.setImageResource(R.drawable.dropdownarrow);
//                }
//                vallue = !vallue;
//            }
//        });
//
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (vallue) {
//                    holder.subcategoriesrecyclerview1.setVisibility(View.VISIBLE);
//                    holder.imgarrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_245);
//                } else {
//                    holder.subcategoriesrecyclerview1.setVisibility(View.GONE);
//                    holder.imgarrow.setImageResource(R.drawable.dropdownarrow);
//                }
//                vallue = !vallue;
//
//
//            }
//        });
        if (data.get(position).getSubcategories() != null) {
            subCategoriesRecylerAadapter1 = new SubCategoriesRecylerAadapter1(activity, data.get(position).getSubcategories());
            holder.subcategoriesrecyclerview1.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
            holder.subcategoriesrecyclerview1.setAdapter(subCategoriesRecylerAadapter1);

        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RecyclerView subcategoriesrecyclerview1;
        //public ImageView imgarrow;
        public MaterialCardView parentView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_lang_name);
            this.subcategoriesrecyclerview1 = itemView.findViewById(R.id.subcategoriesrecyclerview1);
            //this.imgarrow = itemView.findViewById(R.id.imgarrow);
            this.parentView = itemView.findViewById(R.id.parentView);
        }
    }
}


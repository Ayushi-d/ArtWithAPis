package com.example.art_stationary.Adapter.CategoriesAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Activity.ViewproductcategorywiseActivity;
import com.example.art_stationary.Adapter.CategoriesRecylerAdapter;
import com.example.art_stationary.Model.Categories.Datum;
import com.example.art_stationary.Model.Categories.Subcategory;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesRecylerAadapter1 extends RecyclerView.Adapter<SubCategoriesRecylerAadapter1.ViewHolder> {


    private final FragmentActivity activity;
    private final List<Subcategory> subcategories;
    private SubCategoriesRecylerAadapter2 subCategoriesRecylerAadapter2;

    public SubCategoriesRecylerAadapter1(FragmentActivity activity, List<Subcategory> subcategories) {
        this.activity= activity;
        this.subcategories= subcategories;

    }

    // RecyclerView recyclerView;

    @Override
    public SubCategoriesRecylerAadapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.categorieslist_item2, parent, false);
        SubCategoriesRecylerAadapter1.ViewHolder viewHolder = new SubCategoriesRecylerAadapter1.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubCategoriesRecylerAadapter1.ViewHolder holder, int position) {
        String checkingvalue = PreferenceHelper.getInstance(activity).getLangauage();

        if (checkingvalue.equals("ar")){
            holder.textView.setText(subcategories.get(position).getTitlear());
        }else {
            holder.textView.setText(subcategories.get(position).getTitle());

        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewproductcategorywiseActivity.class);
                intent.putExtra("categorycheck","fromoneadapter");
                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("StudentDetails", subcategory.get(position).getSubsubcategories());
                bundle.putParcelableArrayList("categoryone", (ArrayList<? extends Parcelable>) subcategories);
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
//        holder.imageView.setImageResource(listdata[position].getImgId());
        if (subcategories.get(position).getSubsubcategories()!=null){
            subCategoriesRecylerAadapter2 = new SubCategoriesRecylerAadapter2(activity,subcategories.get(position).getSubsubcategories(),subcategories);
            holder.subcategoriesrecyclerview2.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
            holder.subcategoriesrecyclerview2.setAdapter(subCategoriesRecylerAadapter2);

        }



    }


    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RecyclerView subcategoriesrecyclerview2;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_lang_name);
            this.subcategoriesrecyclerview2 =  itemView.findViewById(R.id.subcategoriesrecyclerview2);
        }
    }
}


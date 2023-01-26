package com.example.art_stationary.Adapter.CategoriesAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.art_stationary.Activity.ViewproductcategorywiseActivity;
import com.example.art_stationary.Model.Categories.Subcategory;
import com.example.art_stationary.Model.Categories.Subsubcategory;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesRecylerAadapter2 extends RecyclerView.Adapter<SubCategoriesRecylerAadapter2.ViewHolder> {
    private final ArrayList<Subsubcategory> subsubcategories;
    private final FragmentActivity activity;
    private  List<Subcategory> subcategory;

    public SubCategoriesRecylerAadapter2(FragmentActivity activity, ArrayList<Subsubcategory> subsubcategories, List<Subcategory> subcategories) {
        this.activity = activity;
        this.subsubcategories = subsubcategories;
        this.subcategory = subcategories;
    }


    // RecyclerView recyclerView;

    @Override
    public SubCategoriesRecylerAadapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.subsubcategorieslist_item, parent, false);
        SubCategoriesRecylerAadapter2.ViewHolder viewHolder = new SubCategoriesRecylerAadapter2.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubCategoriesRecylerAadapter2.ViewHolder holder, int position) {
        String checkingvalue = PreferenceHelper.getInstance(activity).getLangauage();
        if (checkingvalue.equals("ar")) {
            holder.textView.setText(subsubcategories.get(position).getTitlear());
        } else {
            holder.textView.setText(subsubcategories.get(position).getTitle());

        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Log.d("checkingid",subcategory.get(position).getSubsubcategories().size()+"");
                Intent intent = new Intent(activity, ViewproductcategorywiseActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("StudentDetails", subcategory.get(position).getSubsubcategories());
                bundle.putParcelableArrayList("StudentDetails", subsubcategories);
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
//        holder.textView.setText(subsubcategories.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return subsubcategories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RecyclerView subcategoriesrecyclerview2;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.tv_lang_name);
        }
    }
}

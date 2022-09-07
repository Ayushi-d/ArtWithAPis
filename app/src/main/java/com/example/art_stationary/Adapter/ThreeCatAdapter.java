package com.example.art_stationary.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.art_stationary.Model.ExpandedCategroryModel;
import com.example.art_stationary.Model.SubCategoryModel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.SecondLevelExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ThreeCatAdapter extends BaseExpandableListAdapter {

    String[] parentHeaders;
    private ArrayList<ExpandedCategroryModel>  parentCategoryList;
    private ArrayList<SubCategoryModel>  subCategoryList;

    List<String[]> secondLevel;
    private Context context;
    List<LinkedHashMap<String, String[]>> data;


    public ThreeCatAdapter(Context context, ArrayList<ExpandedCategroryModel>  parentCategoryList,  List<LinkedHashMap<String, String[]>> data) {
        this.context = context;

        this.parentCategoryList = parentCategoryList;

        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return parentCategoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {
        Log.e(TAG, "isChildSelectable: grouet"+child);


        return child;


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.e(TAG, "isChildSelectable: grouethird"+groupPosition+childPosition);

        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemcategory_expanded, null);
        TextView text = (TextView) convertView.findViewById(R.id.tv_lang_name);
        ExpandedCategroryModel expandedCategroryModel = parentCategoryList.get(groupPosition);
        subCategoryList = expandedCategroryModel.getSubcategories();
        text.setText(expandedCategroryModel.getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

       // SubCategoryModel headers = subCategoryList.get(groupPosition);
        //String[] headers = secondLevel.get(groupPosition);

//        List<String[]> childData = new ArrayList<>();
//        HashMap<String, String[]> secondLevelData=data.get(groupPosition);
//
//        for(String key : secondLevelData.keySet())
//        {
//            childData.add(secondLevelData.get(key));
//        }
//
//        secondLevelELV.setAdapter(new SecondCatAdapter(context, subCategoryList,childData));
//
//        secondLevelELV.setGroupIndicator(null);
//
//        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int previousGroup = -1;
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if(groupPosition != previousGroup)
//                    secondLevelELV.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
//            }
       // });

        return secondLevelELV;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        Log.e(TAG, "isChildSelectable: postionsss"+childPosition+groupPosition);
        return false;
    }
}

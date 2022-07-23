package com.example.art_stationary.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.art_stationary.Fragments.AllcategoriesFragment;
import com.example.art_stationary.Fragments.ItemFragment;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;

import java.util.List;

public class SecondCatAdapter extends BaseExpandableListAdapter {

    private Context context;


    List<String[]> data;

    String[] headers;

    private static Gridhomeadapter.ClickListener mOnClickListener;



    public SecondCatAdapter(Context context, String[] headers, List<String[]> data) {
        this.context = context;
        this.data = data;
        this.headers = headers;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return headers[groupPosition];
    }

    @Override
    public int getGroupCount() {

        return headers.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemsubcategory, null);
        TextView text = (TextView) convertView.findViewById(R.id.child_Title);
        String groupText = getGroup(groupPosition).toString();
        text.setText(groupText);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        String[] childData;

        childData = data.get(groupPosition);


        return childData[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.threecatlayout, null);

        TextView textView = (TextView) convertView.findViewById(R.id.nestedchild_Title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gloabal_View.changeFragment(context, new AllcategoriesFragment());

               // mOnClickListener.onItemClick(childPosition,v);
            }
        });

        String[] childArray=data.get(groupPosition);

        String text = childArray[childPosition];

        textView.setText(text);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String[] children = data.get(groupPosition);


        return children.length;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        Log.e(TAG, "issecondSelectable: grouet"+groupPosition+childPosition);

        return true;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(Gridhomeadapter.ClickListener clickListener) {
        this.mOnClickListener = clickListener;
    }


}
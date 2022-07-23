package com.example.art_stationary.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.art_stationary.Adapter.Searchadapter;
import com.example.art_stationary.Adapter.Viewalladapter;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.Model.Viewallmodel;
import com.example.art_stationary.R;

import java.util.ArrayList;


public class ViewAllFragment extends Fragment {
    SwipeRefreshLayout viewall_list;
    RecyclerView viewall_recyclerlist;
    private ArrayList<Viewallmodel> viewallarraylist;

    public ViewAllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_view_all, container, false);
        viewall_list = v.findViewById(R.id.viewall_list);
        viewall_recyclerlist = v.findViewById(R.id.viewall_recyclerlist);
        viewallarraylist=new ArrayList<>();

        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));
        viewallarraylist.add(new Viewallmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));

        Viewalladapter viewalladapter=new Viewalladapter(viewallarraylist,getActivity());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);

        viewall_recyclerlist.setLayoutManager(linearLayoutManager);
        viewall_recyclerlist.setAdapter(viewalladapter);

        viewall_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewall_list.setRefreshing(false);
            }
        });
        return v;
    }
}
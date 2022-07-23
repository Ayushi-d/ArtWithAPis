package com.example.art_stationary.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Searchadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Utils.Gloabal_View;

import java.util.ArrayList;


public class SearchFragment extends Fragment {
    ConstraintLayout toolbar;
    TextView tooltext;
    SwipeRefreshLayout swipe_listitem;
    RecyclerView searchlist;
    private ArrayList<Searchmodel> searcharraylist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Search");

        swipe_listitem = view.findViewById(R.id.swipe_listitem);
        searchlist = view.findViewById(R.id.searchlist);

        searcharraylist=new ArrayList<>();

        searcharraylist.add(new Searchmodel("Bic Multi Color Ballpoint PensDress","15.000 KWD",R.drawable.custombrands));

        Searchadapter searchadapter=new Searchadapter(searcharraylist,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        searchlist.setLayoutManager(linearLayoutManager);
        searchlist.setAdapter(searchadapter);
        swipe_listitem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_listitem.setRefreshing(false);
            }
        });
        return view;
    }
}
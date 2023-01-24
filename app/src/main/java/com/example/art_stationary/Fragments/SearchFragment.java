package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Searchadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Utils.PreferenceHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;


public class SearchFragment extends Fragment implements ServiceResponse {
    ConstraintLayout toolbar;
    TextView tooltext;
    SwipeRefreshLayout swipe_listitem;
    RecyclerView searchlist;
    private ArrayList<Searchmodel> searcharraylist;
    ConstraintLayout img_back;
    SearchView searchitemlist;
    Searchadapter searchadapter;
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
        img_back = view.findViewById(R.id.img_back);
        tooltext.setText("Search");

        swipe_listitem = view.findViewById(R.id.swipe_listitem);
        searchlist = view.findViewById(R.id.searchlist);
        searchitemlist = view.findViewById(R.id.searchitemlist);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        searcharraylist=new ArrayList<>();

        searchadapter = new Searchadapter(searcharraylist,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        searchlist.setLayoutManager(linearLayoutManager);
        searchlist.setAdapter(searchadapter);

        searchadapter.setOnCartClickListener(new Searchadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });

        searchadapter.setOnItemClickListener(new Searchadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", searcharraylist.get(position).getId());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });

        swipe_listitem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_listitem.setRefreshing(false);
            }
        });

        searchitemlist.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAPI(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchitemlist.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searcharraylist.clear();
                return false;
            }
        });

        return view;
    }

    private void searchAPI(String searchQuery){
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("title",searchQuery));
        new RetrofitService(getActivity(), ServiceUrls.SEARCH, 2, 1, data, this)
                .callService(true);
    }


    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                if (resCode == 200) {
                   // searcharraylist.clear();
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray data = output.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.optJSONObject(i);
                        Searchmodel searchmodel = new Searchmodel();
                        searchmodel.setTitle(object.optString("title"));
                        searchmodel.setPrice(object.optString("price"));
                        searchmodel.setImgid(object.optString("image"));
                        searchmodel.setId(object.optString("id"));
                        searcharraylist.add(searchmodel);
                    }
                    searchadapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---" + e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {

    }
}
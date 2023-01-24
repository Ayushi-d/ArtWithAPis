package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Searchadapter;
import com.example.art_stationary.Adapter.Viewalladapter;
import com.example.art_stationary.Model.BannerModel;
import com.example.art_stationary.Model.BrandModel;
import com.example.art_stationary.Model.Mostpopularmodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Searchmodel;
import com.example.art_stationary.Model.Viewallmodel;
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


public class ViewAllFragment extends Fragment implements ServiceResponse {
    SwipeRefreshLayout viewall_list;
    RecyclerView viewall_recyclerlist;
    private ArrayList<Recyclerhomemodel> viewallarraylist;
    Viewalladapter viewalladapter;
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

        if (getArguments().getString("brandid").isEmpty() && !getArguments().get("viewAllID").equals("offers")){
            getAllProducts();
        }else if (getArguments().get("viewAllID").equals("offers")){
            getHomePageOffersProduct();
        }else{
            String brandID = getArguments().getString("brandid");
            getProductByBrand(brandID);
        }

        viewallarraylist=new ArrayList<>();

        viewalladapter=new Viewalladapter(viewallarraylist,getActivity());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        viewall_recyclerlist.setLayoutManager(linearLayoutManager);
        viewalladapter.setOnItemClickListener(new Viewalladapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (getArguments().getString("viewAllID") == "brands"){
                    Bundle bundle = new Bundle();
                    bundle.putString("brandid", viewallarraylist.get(position).getid());
                    bundle.putString("viewAllID","");
                    ViewAllFragment viewAllFragment = new ViewAllFragment();
                    viewAllFragment.setArguments(bundle);
                    Gloabal_View.changeFragment(getActivity(), viewAllFragment);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("prodid", viewallarraylist.get(position).getid());
                    ItemFragment itemFragment = new ItemFragment();
                    itemFragment.setArguments(bundle);
                    Gloabal_View.changeFragment(getActivity(), itemFragment);
                }
            }
        });
        viewall_recyclerlist.setAdapter(viewalladapter);

        viewall_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewall_list.setRefreshing(false);
            }
        });
        return v;
    }

    private void getAllProducts() {
        new RetrofitService(getContext(), ServiceUrls.HOMEAPI,
                1, 1, this).callService(true);
    }

    private void getProductByBrand(String brandId) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("brandid",brandId));
        new RetrofitService(getActivity(), ServiceUrls.GETPRODUCTSBYBRAND, 2, 2, data, this)
                .callService(true);
    }

    private void getHomePageOffersProduct() {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("type","offerprods"));
        new RetrofitService(getContext(), ServiceUrls.GETPRODUCTBYOFFER,
                2, 3,data, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    Log.d(TAG, "onServiceResponse: Outpuut Response---"+output.toString());
                    JSONArray newArrivalsArray = new JSONArray();
                    if (getArguments().getString("viewAllID") == "newArrivals"){
                        newArrivalsArray  =  output.getJSONArray("newarrivals");

                    }else if (getArguments().getString("viewAllID") == "brands"){
                        newArrivalsArray  =  output.getJSONArray("brands");

                    }else if (getArguments().getString("viewAllID") == "mostPopular"){
                        newArrivalsArray  =  output.getJSONArray("mostpopular");
                    }
                    for (int i = 0; i < newArrivalsArray.length(); i++){
                        JSONObject data = newArrivalsArray.optJSONObject(i);
                        Recyclerhomemodel newArrivalModel = new Recyclerhomemodel();
                        newArrivalModel.setTitle(data.optString("title"));
                        newArrivalModel.setPrice(data.optString("price"));
                        newArrivalModel.setImgid(data.optString("image"));
                        newArrivalModel.setid(data.optString("id"));
                        viewallarraylist.add(newArrivalModel);
                    }
                    viewalladapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        } else if (requestCode == 3){
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray offersarray = output.optJSONArray("data");
                    for (int i = 0; i < offersarray.length(); i++){
                        JSONObject data = offersarray.optJSONObject(i);
                        Recyclerhomemodel newArrivalModel = new Recyclerhomemodel();
                        newArrivalModel.setTitle(data.optString("title"));
                        newArrivalModel.setPrice(data.optString("price"));
                        newArrivalModel.setImgid(data.optString("image"));
                        newArrivalModel.setid(data.optString("id"));
                        viewallarraylist.add(newArrivalModel);
                    }
                    viewalladapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        }
        else{
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray newArrivalsArray =  output.getJSONArray("data");
                    for (int i = 0; i < newArrivalsArray.length(); i++){
                        JSONObject data = newArrivalsArray.optJSONObject(i);
                        Recyclerhomemodel newArrivalModel = new Recyclerhomemodel();
                        newArrivalModel.setTitle(data.optString("title"));
                        newArrivalModel.setPrice(data.optString("price"));
                        newArrivalModel.setid(data.optString("id"));
                        JSONArray imagesArr = data.optJSONArray("images");
                        JSONObject imgObj = imagesArr.optJSONObject(0);
                        newArrivalModel.setImgid(imgObj.optString("image"));
                        viewallarraylist.add(newArrivalModel);
                    }
                    viewalladapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
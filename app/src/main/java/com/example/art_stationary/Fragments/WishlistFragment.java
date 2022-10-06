package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Viewalladapter;
import com.example.art_stationary.Adapter.WishListAdapter;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class WishlistFragment extends Fragment implements ServiceResponse {

    RecyclerView wishListRecyclerView;
    private ArrayList<Recyclerhomemodel> wishListArrayList;
    WishListAdapter wishListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wishlist, container, false);
        wishListRecyclerView = view.findViewById(R.id.wishlistRecycler);
        getWishListProducts();
        wishListArrayList = new ArrayList<>();
        wishListAdapter = new WishListAdapter(wishListArrayList,getActivity());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);
        wishListRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.setOnItemClickListener(new Viewalladapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                removeFromWishList(wishListArrayList.get(position).getid());
            }
        });
        return view;
    }

    private void getWishListProducts(){
        String userid = PreferenceHelper.getInstance(getActivity()).getid();
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("userid",userid));
        new RetrofitService(getActivity(), ServiceUrls.GETMYWISHLIST, 2, 1, data, this)
                .callService(true);
    }

    private void removeFromWishList(String wishListId){
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("wishlistid",wishListId));
        new RetrofitService(getActivity(), ServiceUrls.REMOVEFROMWISHLIST, 2, 2, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    Log.d(TAG, "onServiceResponse: Outpuut Response---"+output.toString());

                    JSONArray data =  output.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++){
                        JSONObject object = data.optJSONObject(i);
                        Recyclerhomemodel wishListModel = new Recyclerhomemodel();
                        wishListModel.setTitle(object.optString("prodname"));
                        //wishListModel.setPrice(object.optString("price"));
                        wishListModel.setImgid(object.optString("image"));
                        wishListModel.setid(object.optString("id"));
                        wishListArrayList.add(wishListModel);
                    }
                    wishListAdapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e);
                e.printStackTrace();
            }
        }else{
            try {
                if (resCode==200) {
                    Toast.makeText(getActivity(), "Product Removed Successfully", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
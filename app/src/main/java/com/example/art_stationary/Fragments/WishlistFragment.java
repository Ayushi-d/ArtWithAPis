package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Viewalladapter;
import com.example.art_stationary.Adapter.WishListAdapter;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class WishlistFragment extends Fragment implements ServiceResponse {

    RecyclerView wishListRecyclerView;
    ArrayList<Recyclerhomemodel> wishListArrayList;
    WishListAdapter wishListAdapter;
    BottomNavigationView navBar;
    ConstraintLayout toolbar;
    TextView tooltext,emptyWishListText;
    ConstraintLayout img_back;
    int postionRemoved;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wishlist, container, false);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = view.findViewById(R.id.toolheadtext);
        emptyWishListText = view.findViewById(R.id.emptyWishListText);
        img_back = view.findViewById(R.id.img_back);
        tooltext.setText("WishList");
        wishListRecyclerView = view.findViewById(R.id.wishlistRecycler);

        getWishListProducts();


        wishListArrayList = new ArrayList<>();
        wishListAdapter = new WishListAdapter(wishListArrayList,getActivity());
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);

        wishListAdapter.setOnClickListener(new WishListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", wishListArrayList.get(position).getProdid());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });

        wishListAdapter.setOnItemClickListener(new WishListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                postionRemoved = position;
                Log.d(TAG, "onItemClick: ----bc"+position);
                removeFromWishList(wishListArrayList.get(position).getid());
            }
        });

        wishListRecyclerView.setAdapter(wishListAdapter);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
                .callService(false);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                   // if (wishListArrayList.size() != 0){
                        wishListArrayList.clear();
                   // }
                        JSONObject output = jsonObject.getJSONObject("output");
                        JSONArray data =  output.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++){
                            JSONObject object = data.optJSONObject(i);
                            Recyclerhomemodel wishListModel = new Recyclerhomemodel();
                            wishListModel.setTitle(object.optString("prodname"));
                            wishListModel.setPrice("1");
                            wishListModel.setImgid(object.optString("image"));
                            wishListModel.setid(object.optString("id"));
                            wishListModel.setProdid(object.optString("prodid"));
                            wishListArrayList.add(wishListModel);
                        }
                        wishListAdapter.notifyDataSetChanged();
                    }
            } catch (Exception e) {
                Log.d(TAG, "onServiceResponse: API Error---"+e);
                e.printStackTrace();
            }
            if (wishListArrayList.size() == 0){
                emptyWishListText.setVisibility(View.VISIBLE);
                wishListArrayList.clear();
                wishListAdapter.notifyDataSetChanged();
            }
        }else if (requestCode == 2){
            try {
                if (resCode==200) {
                    getWishListProducts();
//                    wishListArrayList.remove(postionRemoved);
//                    wishListAdapter.notifyItemRemoved(postionRemoved);
//                    Toast.makeText(getActivity(), "Product Removed Successfully", Toast.LENGTH_SHORT).show();
//                    if (wishListArrayList.size() == 0){
//                        emptyWishListText.setVisibility(View.VISIBLE);
//                    }
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
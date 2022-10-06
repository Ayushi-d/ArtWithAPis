package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Gridhomeadapter;
import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Verticalhomeadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.Verticallistmodel;
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


public class CartFragment extends Fragment implements ServiceResponse {
     RecyclerView cartlist;
     Button button_checkout;
     ConstraintLayout toolbar;
     TextView tooltext;
     TextView text_subtotalprice,empty_text;
     BottomNavigationView navBar;
     ConstraintLayout img_back;
     SwipeRefreshLayout swipe_list;
     Mycartadapter mycartadapter;
     ArrayList<String> imglst = new ArrayList<>();
     int postionRemoved;
     double subTotalPrice;

    private ArrayList<Cartmodel> cartmodelArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        swipe_list = view.findViewById(R.id.swipe_list);
        cartlist = view.findViewById(R.id.cartlist);
        img_back =view.findViewById(R.id.img_back);
        empty_text =view.findViewById(R.id.empty_text);
        button_checkout = view.findViewById(R.id.button_checkout);
        text_subtotalprice = view.findViewById(R.id.text_subtotalprice);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);
        navBar.setSelectedItemId(R.id.cart);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Cart");
        getCartItems();

        button_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gloabal_View.changeFragment(getActivity(), new CheckoutFragment());
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        cartmodelArrayList=new ArrayList<>();

        mycartadapter = new Mycartadapter(cartmodelArrayList,imglst,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        cartlist.setLayoutManager(linearLayoutManager);
        mycartadapter.setOnItemClickListener(new Mycartadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("prodid", cartmodelArrayList.get(position).getProdid());
                ItemFragment itemFragment = new ItemFragment();
                itemFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), itemFragment);
            }
        });

        mycartadapter.setOnDeleteItemClickListener(new Mycartadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
              //  Log.d(TAG, "onItemClick: ---"+cartmodelArrayList.get(position).getCartID());
                postionRemoved = position;
                deleteCartItems(cartmodelArrayList.get(position).getId());
            }
        });
        cartlist.setAdapter(mycartadapter);

        swipe_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_list.setRefreshing(false);
            }
        });

        return view;
    }

    private void getCartItems() {
        List<MultipartBody.Part> data = new ArrayList<>();
        String userID = PreferenceHelper.getInstance(getActivity()).getid();
        data.add(MultipartBody.Part.createFormData("userid",userID));
        new RetrofitService(getActivity(), ServiceUrls.GETCARTITEMS, 2, 1, data, this)
                .callService(true);
    }

    private void deleteCartItems(String cartID) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("cartid",cartID));
        new RetrofitService(getActivity(), ServiceUrls.DELETECARTITEMS, 2, 2, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    cartmodelArrayList.clear();
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    text_subtotalprice.setText(getString(R.string.global_currency,output.optString("subtotal")));
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject cartData = jsonArray.getJSONObject(i);
                        Cartmodel cartmodel = new Cartmodel();
                        cartmodel.setProdname(cartData.optString("prodname"));
                        cartmodel.setPrice(cartData.optString("product_price"));
                        cartmodel.setQuantity(cartData.optString("quantity"));
                        subTotalPrice = subTotalPrice + Double.parseDouble(cartData.optString("product_price"));
                        cartmodel.setCartID(cartData.optString("cartid"));
                        cartmodel.setId(cartData.optString("id"));
                        cartmodel.setProdid(cartData.optString("prodid"));
                        JSONArray imageArray = cartData.optJSONArray("images");
                        JSONObject imageData = imageArray.optJSONObject(0);
                        imglst.add(imageData.optString("image"));
                        cartmodelArrayList.add(cartmodel);
                    }
                    mycartadapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cartmodelArrayList.size() == 0){
                empty_text.setVisibility(View.VISIBLE);
                cartmodelArrayList.clear();
                text_subtotalprice.setText(getString(R.string.global_currency,"00.000"));
                mycartadapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == 2){
            if (resCode==200) {
                Log.d(TAG, "onServiceResponse: postion---"+postionRemoved);
                getCartItems();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
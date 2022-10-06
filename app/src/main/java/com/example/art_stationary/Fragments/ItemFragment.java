package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.Colorhomeadapter;
import com.example.art_stationary.Adapter.ImagePagerAdapter;
import com.example.art_stationary.Adapter.Sizeadapter;
import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.Model.Sizemodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class ItemFragment extends Fragment implements ServiceResponse {
    ConstraintLayout constraintLayout;
    ImageView img_back,img_add,img_minus,likeButton;
    TextView text_itemname,textprice,textaboutdescription,text_item;
    RecyclerView listcolor;
    Button button_addtocart;
    private ArrayList<Colormodel> recyclercolorarraylist;
    private ArrayList<Sizemodel> sizeArrayList;
    BottomNavigationView navBar;
    RecyclerView recyclersizrchart;
    ViewPager imageViewPager;
    ImagePagerAdapter imagePagerAdapter;
    ArrayList<String> imglst = new ArrayList<>();
    String productQuantity = "";


    public ItemFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_item, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        constraintLayout = view.findViewById(R.id.constraintLayout);
        img_back = view.findViewById(R.id.img_back);
        img_add = view.findViewById(R.id.img_add);
        img_minus = view.findViewById(R.id.img_minus);
        likeButton = view.findViewById(R.id.likeButton);
        text_item = view.findViewById(R.id.text_item);
        imageViewPager = view.findViewById(R.id.imageViewPager);
        text_itemname = view.findViewById(R.id.text_itemname);
        textprice = view.findViewById(R.id.textprice);
        textaboutdescription = view.findViewById(R.id.textaboutdescription);
        listcolor = view.findViewById(R.id.listcolor);
        recyclersizrchart = view.findViewById(R.id.recyclersizrchart);
        button_addtocart = view.findViewById(R.id.button_addtocart);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        getProductDetail(getArguments().getString("prodid"));

        imagePagerAdapter = new ImagePagerAdapter(getActivity(),imglst);
        imageViewPager.setAdapter(imagePagerAdapter);

        imageViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        button_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToCart();
                //Gloabal_View.changeFragment(getActivity(), new CartFragment());
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(text_item.getText().toString()) < Integer.parseInt(productQuantity) ){
                    int count = Integer.parseInt(text_item.getText().toString())+1;
                    text_item.setText(""+count);
                }
            }
        });
        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(text_item.getText().toString()) > 1){
                    int count = Integer.parseInt(text_item.getText().toString())-1;
                    text_item.setText(""+count);
                }
            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToWishlist();
            }
        });


        recyclercolorarraylist = new ArrayList<>();

        // added data to grid array list
        recyclercolorarraylist.add(new Colormodel(R.color.grey));
        recyclercolorarraylist.add(new Colormodel(R.color.grey));
        recyclercolorarraylist.add(new Colormodel(R.color.buttongreen));
        Colorhomeadapter adapter = new Colorhomeadapter(recyclercolorarraylist, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        listcolor.setLayoutManager(layoutManager);
        listcolor.setAdapter(adapter);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        //size list
        sizeArrayList=new ArrayList<>();
        sizeArrayList.add(new Sizemodel("M"));
        sizeArrayList.add(new Sizemodel("S"));
        sizeArrayList.add(new Sizemodel("L"));
        sizeArrayList.add(new Sizemodel("XL"));
        Sizeadapter sizeadapter=new Sizeadapter(sizeArrayList,getActivity());

        recyclersizrchart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 4;
                return true;
            }
        });
        recyclersizrchart.setAdapter(sizeadapter);
        return view;
    }

    private void setProductData(JSONObject data){
        text_itemname.setText(data.optString("title"));
        productQuantity = data.optString("quantity");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textaboutdescription.setText(Html.fromHtml(data.optString("description"), Html.FROM_HTML_MODE_COMPACT));
        } else {
            textaboutdescription.setText(Html.fromHtml(data.optString("description")));
        }
        textprice.setText(data.optString("price"));
        JSONArray jsonArray = data.optJSONArray("images");
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject imagedata = jsonArray.optJSONObject(i);
            imglst.add(imagedata.optString("image"));
        }
        imagePagerAdapter.notifyDataSetChanged();
    }


    private void getProductDetail(String prodID) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("prodid",prodID));
        new RetrofitService(getActivity(), ServiceUrls.GETPRODUCTDETAIL, 2, 1, data, this)
                .callService(true);
    }

    private void addItemToWishlist(){
        List<MultipartBody.Part> data = new ArrayList<>();
        String userID = PreferenceHelper.getInstance(getActivity()).getid();
        data.add(MultipartBody.Part.createFormData("userid",userID));
        data.add(MultipartBody.Part.createFormData("prodid",getArguments().getString("prodid")));
        new RetrofitService(getActivity(), ServiceUrls.ADDTOWISHLIST, 2, 3, data, this)
                .callService(true);
    }

    private void addItemToCart() {
        List<MultipartBody.Part> data = new ArrayList<>();
        String userID = PreferenceHelper.getInstance(getActivity()).getid();
        data.add(MultipartBody.Part.createFormData("userid",userID));
        data.add(MultipartBody.Part.createFormData("prodid",getArguments().getString("prodid")));
        data.add(MultipartBody.Part.createFormData("quantity",text_item.getText().toString()));
        data.add(MultipartBody.Part.createFormData("customtext",""));
        data.add(MultipartBody.Part.createFormData("civilid",""));
        data.add(MultipartBody.Part.createFormData("image",""));
        data.add(MultipartBody.Part.createFormData("sizeid",""));
        data.add(MultipartBody.Part.createFormData("colorid",""));
        data.add(MultipartBody.Part.createFormData("price",textprice.getText().toString()));
        new RetrofitService(getActivity(), ServiceUrls.ADDTOCART, 2, 2, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    JSONObject data = jsonArray.getJSONObject(0);
                    Log.d(TAG, "onServiceResponse: data---"+data);
                    setProductData(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (requestCode == 2){
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    Toast.makeText(getActivity(), "Item added to cart Successfully", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    Toast.makeText(getActivity(), "Item added to WishList Successfully", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){

            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
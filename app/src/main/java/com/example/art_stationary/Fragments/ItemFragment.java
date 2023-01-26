package com.example.art_stationary.Fragments;

import static android.service.controls.ControlsProviderService.TAG;

import android.graphics.Paint;
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
import com.example.art_stationary.Adapter.Mostpopularadapter;
import com.example.art_stationary.Adapter.OtherProductsAdapter;
import com.example.art_stationary.Adapter.Sizeadapter;
import com.example.art_stationary.Model.Colormodel;
import com.example.art_stationary.Model.CombinationModel;
import com.example.art_stationary.Model.Recyclerhomemodel;
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
    TextView text_itemname,textprice,textaboutdescription,text_item,text_selectquantity,textOutOfStock,offerPrice;
    RecyclerView listcolor;
    Button button_addtocart;
    private ArrayList<Sizemodel> sizeArrayList;
    BottomNavigationView navBar;
    RecyclerView recyclersizrchart;
    RecyclerView otherProductsRV;
    ViewPager imageViewPager;
    ImagePagerAdapter imagePagerAdapter;
    OtherProductsAdapter otherProductsAdapter;
    ArrayList<String> imglst = new ArrayList<>();
    String productQuantity = "";
    private ArrayList<CombinationModel> combinationModelList = new ArrayList<>();
    Sizeadapter sizeadapter;
    Colorhomeadapter colorhomeadapter;
    String ColorID = "";
    String SizeID = "";
    double totalprice = 0;

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
        text_selectquantity = view.findViewById(R.id.text_selectquantity);
        imageViewPager = view.findViewById(R.id.imageViewPager);
        text_itemname = view.findViewById(R.id.text_itemname);
        textOutOfStock = view.findViewById(R.id.textOutOfStock);
        textprice = view.findViewById(R.id.textprice);
        textaboutdescription = view.findViewById(R.id.textaboutdescription);
        offerPrice = view.findViewById(R.id.offerPrice);
        listcolor = view.findViewById(R.id.listcolor);
        recyclersizrchart = view.findViewById(R.id.recyclersizrchart);
        button_addtocart = view.findViewById(R.id.button_addtocart);
        otherProductsRV = view.findViewById(R.id.otherProductsRV);
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
                    textprice.setText(getString(R.string.global_currency,""+(totalprice*Double.parseDouble(""+count))+"00"));
                }else{
                    Toast.makeText(getActivity(), "Maximum Quantity for the product Already Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(text_item.getText().toString()) > 1){
                    int count = Integer.parseInt(text_item.getText().toString())-1;
                    text_item.setText(""+count);
                    textprice.setText(getString(R.string.global_currency,""+(totalprice*Double.parseDouble(""+count))+"00"));
                }
            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToWishlist();
            }
        });


        colorhomeadapter = new Colorhomeadapter(combinationModelList, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        listcolor.setLayoutManager(layoutManager);
        listcolor.setAdapter(colorhomeadapter);
        colorhomeadapter.setOnItemClickListener(new Colorhomeadapter.ClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(int position, View v) {
                CombinationModel combinationModel = combinationModelList.get(position);
                combinationModelList.stream().filter( combination -> combination.getSelectedColor().equals(true)).forEach(combination -> combination.setSelectedColor(false));
                combinationModel.setSelectedColor(true);
                ColorID = combinationModel.getColorid();
                SizeID = combinationModel.getSizeid();
                sizeadapter.notifyDataSetChanged();
                colorhomeadapter.notifyDataSetChanged();
                resetQuantity(combinationModel);
                offerPrice.setText(combinationModel.getSaleprice().equals("")  ? "" : getString(R.string.global_currency,combinationModel.getPrice()) );
                offerPrice.setPaintFlags(offerPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                textprice.setText(combinationModel.getSaleprice().equals("") ? getString(R.string.global_currency,combinationModel.getPrice()) : getString(R.string.global_currency,combinationModel.getSaleprice()));
                totalprice = combinationModel.getSaleprice().equals("") ? Double.parseDouble( combinationModel.getPrice()) : Double.parseDouble(combinationModel.getSaleprice());
            }
        });

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        //size list
        sizeadapter = new Sizeadapter(combinationModelList,getActivity());
        recyclersizrchart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 4;
                return true;
            }
        });
        recyclersizrchart.setAdapter(sizeadapter);

        sizeadapter.setOnItemClickListener(new Sizeadapter.ClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(int position, View v) {
                CombinationModel combinationModel = combinationModelList.get(position);
                combinationModelList.stream().filter( combination -> combination.getSelectedColor().equals(true)).forEach(combination -> combination.setSelectedColor(false));
                combinationModel.setSelectedColor(true);
                ColorID = combinationModel.getColorid();
                SizeID = combinationModel.getSizeid();
                sizeadapter.notifyDataSetChanged();
                colorhomeadapter.notifyDataSetChanged();
                resetQuantity(combinationModel);
                offerPrice.setText(combinationModel.getSaleprice().equals("")  ? "" : getString(R.string.global_currency,combinationModel.getPrice()) );
                offerPrice.setPaintFlags(offerPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                textprice.setText(combinationModel.getSaleprice().equals("") ? getString(R.string.global_currency,combinationModel.getPrice()) : getString(R.string.global_currency,combinationModel.getSaleprice()));
                totalprice = combinationModel.getSaleprice().equals("") ? Double.parseDouble( combinationModel.getPrice()) : Double.parseDouble(combinationModel.getSaleprice());
            }
        });

        otherProductsAdapter = new OtherProductsAdapter(getActivity());
        LinearLayoutManager mostpopularmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        otherProductsRV.setLayoutManager(mostpopularmanager);
        otherProductsRV.setAdapter(otherProductsAdapter);


        return view;
    }

    private void resetQuantity(CombinationModel combinationModel){
        text_item.setText("1");
        productQuantity = "0";
        productQuantity = combinationModel.getQuantity();
    }

    private void setProductData(JSONObject data){
        String checkingvalue = PreferenceHelper.getInstance(getActivity()).getLangauage();
//        productQuantity = data.optString("quantity");
        if (checkingvalue.equals("ar")){
            text_itemname.setText(data.optString("titlear"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textaboutdescription.setText(Html.fromHtml(data.optString("descriptionar"), Html.FROM_HTML_MODE_COMPACT));
            } else {
                textaboutdescription.setText(Html.fromHtml(data.optString("descriptionar")));
            }
        }else {
            text_itemname.setText(data.optString("title"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textaboutdescription.setText(Html.fromHtml(data.optString("description"), Html.FROM_HTML_MODE_COMPACT));
            } else {
                textaboutdescription.setText(Html.fromHtml(data.optString("description")));
            }
        }

              JSONArray jsonArray = data.optJSONArray("images");
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject imagedata = jsonArray.optJSONObject(i);
            imglst.add(imagedata.optString("image"));
        }
        imagePagerAdapter.notifyDataSetChanged();


        if (!data.isNull("combinations")){
            try{
                JSONArray combinationArray =  data.getJSONArray("combinations");
                for (int i = 0; i < combinationArray.length(); i++){
                    JSONObject combinationData = combinationArray.optJSONObject(i);
                    CombinationModel combinationModel = new CombinationModel();
                    combinationModel.setColorid(combinationData.optString("colorid"));
                    combinationModel.setColorCode(combinationData.optString("colorcode"));
                    combinationModel.setPrice(combinationData.optString("price"));
                    combinationModel.setSaleprice(combinationData.isNull("saleprice") ? "" : combinationData.optString("saleprice"));
                    combinationModel.setSizename(combinationData.optString("sizename"));
                    combinationModel.setSizeid(combinationData.optString("sizeid"));
                    combinationModel.setQuantity(combinationData.optString("quantity"));
                    if (i==0){
                        ColorID = combinationData.optString("colorid");
                        SizeID = combinationData.optString("sizeid");
                        combinationModel.setSelectedColor(true);

                        productQuantity = combinationData.optString("quantity");
                    }
                    combinationModelList.add(combinationModel);
                }
                colorhomeadapter.notifyDataSetChanged();
                sizeadapter.notifyDataSetChanged();
                offerPrice.setText(combinationModelList.get(0).getSaleprice().equals("")  ? "" : getString(R.string.global_currency,combinationModelList.get(0).getPrice()) );
                offerPrice.setPaintFlags(offerPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                textprice.setText(combinationModelList.get(0).getSaleprice().equals("") ? getString(R.string.global_currency,combinationModelList.get(0).getPrice()) : getString(R.string.global_currency,combinationModelList.get(0).getSaleprice()));
                totalprice = combinationModelList.get(0).getSaleprice().equals("") ? Double.parseDouble( combinationModelList.get(0).getPrice()) : Double.parseDouble(combinationModelList.get(0).getSaleprice());

//                offerPrice.setText(data.optString("saleprice").equals("") || data.isNull("saleprice") ? ""  : getString(R.string.global_currency,data.optString("price")) );
//                offerPrice.setPaintFlags(offerPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
//                textprice.setText(data.optString("saleprice").equals("") || data.isNull("saleprice") ? getString(R.string.global_currency,data.optString("price")) : getString(R.string.global_currency,data.optString("saleprice")));
            }catch (Exception e){

            }
        }else{
            offerPrice.setText(data.optString("saleprice").equals("") || data.isNull("saleprice") ? "" : getString(R.string.global_currency,data.optString("price")) );
            offerPrice.setPaintFlags(offerPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            textprice.setText(data.optString("saleprice").equals("") || data.isNull("saleprice") ? getString(R.string.global_currency,data.optString("price")) : getString(R.string.global_currency,data.optString("saleprice")));
            productQuantity = data.optString("quantity");
        }

        if (Integer.parseInt(productQuantity) == 0){
            textOutOfStock.setVisibility(View.VISIBLE);
            img_minus.setVisibility(View.GONE);
            img_add.setVisibility(View.GONE);
            text_item.setVisibility(View.GONE);
            text_selectquantity.setVisibility(View.GONE);
        }

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
        data.add(MultipartBody.Part.createFormData("sizeid",SizeID));
        data.add(MultipartBody.Part.createFormData("colorid",ColorID));
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
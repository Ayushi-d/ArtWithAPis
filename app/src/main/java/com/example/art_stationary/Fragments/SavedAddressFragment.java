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

import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.art_stationary.Adapter.Mycartadapter;
import com.example.art_stationary.Adapter.Ordernoadapter;
import com.example.art_stationary.Adapter.Savedaddressadapter;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.OrdernoModel;
import com.example.art_stationary.Model.Savedaddressmodel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.Gloabal_View;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class SavedAddressFragment extends Fragment implements ServiceResponse, Serializable {
    RecyclerView savedaddresslist;
    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    Savedaddressadapter savedaddressadapter;
    ConstraintLayout img_back;


    private ArrayList<Savedaddressmodel> savedaddressmodelArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_address, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);
        savedaddresslist=view.findViewById(R.id.savedaddresslist);
        img_back = view.findViewById(R.id.img_back);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = view.findViewById(R.id.toolheadtext);
        tooltext.setText("Saved Addresss");

        getMyAddress();

        savedaddressmodelArrayList=new ArrayList<>();
        savedaddressadapter = new Savedaddressadapter(savedaddressmodelArrayList,getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        savedaddresslist.setLayoutManager(linearLayoutManager);
        savedaddresslist.setAdapter(savedaddressadapter);

        savedaddressadapter.setOnDeleteItemClickListener(new Mycartadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                deleteAddress(savedaddressmodelArrayList.get(position).getId());
            }
        });

        savedaddressadapter.setOnEditItemClickListener(new Mycartadapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putString("addressID",savedaddressmodelArrayList.get(position).getId());
                AddressFragment addressFragment = new AddressFragment();
                addressFragment.setArguments(bundle);
                Gloabal_View.changeFragment(getActivity(), addressFragment);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void getMyAddress() {
        List<MultipartBody.Part> data = new ArrayList<>();
        String userID = PreferenceHelper.getInstance(getActivity()).getid();
        data.add(MultipartBody.Part.createFormData("loginid",userID));
        new RetrofitService(getActivity(), ServiceUrls.MYADDRESS, 2, 1, data, this)
                .callService(true);
    }

    private void deleteAddress(String addressID) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("addressid",addressID));
        new RetrofitService(getActivity(), ServiceUrls.DELETEADDRESS, 2, 2, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode==200) {
                    savedaddressmodelArrayList.clear();
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray =  output.getJSONArray("data");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject addressData = jsonArray.getJSONObject(i);
                        Savedaddressmodel savedaddressmodel = new Savedaddressmodel();
                        savedaddressmodel.setShip_appartment(addressData.optString("ship_appartment"));
                        savedaddressmodel.setShip_avenue(addressData.optString("ship_avenue"));
                        savedaddressmodel.setShip_block(addressData.optString("ship_block"));
                        savedaddressmodel.setShip_street(addressData.optString("ship_street"));
                        savedaddressmodel.setShip_floor(addressData.optString("ship_floor"));
                        savedaddressmodel.setShip_building(addressData.optString("ship_building"));
                        savedaddressmodel.setId(addressData.optString("id"));
                        savedaddressmodelArrayList.add(savedaddressmodel);
                    }
                    savedaddressadapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (savedaddressmodelArrayList.size() == 0){
                //empty_text.setVisibility(View.VISIBLE);
                savedaddressmodelArrayList.clear();
                savedaddressadapter.notifyDataSetChanged();
            }
        }else{
            if (resCode==200) {
                getMyAddress();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {

    }
}
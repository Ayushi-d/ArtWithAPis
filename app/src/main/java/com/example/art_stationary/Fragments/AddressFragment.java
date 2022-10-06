package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Model.AreaModel;
import com.example.art_stationary.Model.Cartmodel;
import com.example.art_stationary.Model.GovernateModel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;


public class AddressFragment extends Fragment implements ServiceResponse {
    ConstraintLayout toolbar;
    TextView tooltext;
    EditText et_block,et_street,et_avenue,et_buildingno,et_floorno,et_flatno,et_direction;
    BottomNavigationView navBar;
    ConstraintLayout img_back;
    Spinner et_governate;
    Spinner et_area;
    Button button_addAddress;
    ArrayList<GovernateModel> governateList = new ArrayList<>();
    ArrayList<String> governateNameList = new ArrayList<>();
    ArrayList<AreaModel> areaList = new ArrayList<>();
    ArrayList<String> areaNameList = new ArrayList<>();
    String govID = "";
    String areaID = "";
    String addressID = "";


    public AddressFragment() {
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
        View view= inflater.inflate(R.layout.fragment_address, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        img_back =view.findViewById(R.id.img_back);
        et_governate =view.findViewById(R.id.et_governate);
        et_block =view.findViewById(R.id.et_block);
        et_avenue =view.findViewById(R.id.et_avenue);
        et_street =view.findViewById(R.id.et_street);
        et_buildingno =view.findViewById(R.id.et_buildingno);
        et_floorno =view.findViewById(R.id.et_floorno);
        et_flatno =view.findViewById(R.id.et_flatno);
        et_direction =view.findViewById(R.id.et_direction);
        et_area =view.findViewById(R.id.et_area);
        button_addAddress =view.findViewById(R.id.button_addAddress);
        tooltext.setText("Address");
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.GONE);

        if (!getArguments().getString("addressID").isEmpty()){
            addressID = getArguments().getString("addressID");
            getAddressById(addressID);
        }

        getGovernates();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        button_addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOrEditAddress();
            }
        });
        return view;
    }

    private void setupGovernateSpinner(){
        et_governate.setOnItemSelectedListener(new GovernateClass());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, governateNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_governate.setAdapter(dataAdapter);
    }

    private void setupAreaSpinner(){
        et_area.setOnItemSelectedListener(new AreaClass());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, areaNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_area.setAdapter(dataAdapter);
    }


    private void getGovernates() {
        new RetrofitService(getActivity(), ServiceUrls.GETGOVERNATES, 1, 1, this)
                .callService(true);
    }

    private void getAreas(String govCode) {
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("governoratecode",govCode));
        new RetrofitService(getActivity(), ServiceUrls.GETAREAS, 2, 2, data, this)
                .callService(true);
    }

    private void getAddressById(String addressID){
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("addressid",addressID));
        new RetrofitService(getActivity(), ServiceUrls.GETADRESSBYID, 2, 4, data, this)
                .callService(true);
    }

    private void AddOrEditAddress(){
        List<MultipartBody.Part> data = new ArrayList<>();
        String userid = PreferenceHelper.getInstance(getActivity()).getid();
        if (addressID.isEmpty()){
            data.add(MultipartBody.Part.createFormData("userid",userid));
        }else{
            data.add(MultipartBody.Part.createFormData("addressid",addressID));
        }
        data.add(MultipartBody.Part.createFormData("ship_street",et_street.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_building",et_buildingno.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_appartment",et_flatno.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_block",et_block.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_avenue",et_avenue.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_floor",et_floorno.getText().toString()));
        data.add(MultipartBody.Part.createFormData("ship_govid",govID));
        data.add(MultipartBody.Part.createFormData("ship_gareaid",areaID));
        new RetrofitService(getActivity(), addressID.isEmpty() ? ServiceUrls.ADDADDRESS : ServiceUrls.EDITADDRESS , 2, 3, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode == 200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray = output.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.optJSONObject(i);
                        GovernateModel governateModel = new GovernateModel();
                        governateModel.setAreafullname(data.optString("areafullname"));
                        governateNameList.add(data.optString("areafullname"));
                        governateModel.setArea(data.optString("area"));
                        governateModel.setId(data.optString("id"));
                        governateList.add(governateModel);
                    }
                    setupGovernateSpinner();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (requestCode == 2){
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (resCode == 200) {
                    JSONObject output = jsonObject.getJSONObject("output");
                    JSONArray jsonArray = output.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.optJSONObject(i);
                        AreaModel areaModel = new AreaModel();
                        areaModel.setAreafullname(data.optString("areafullname"));
                        areaNameList.add(data.optString("areafullname"));
                        areaModel.setId(data.optString("id"));
                        areaList.add(areaModel);
                    }
                    setupAreaSpinner();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (requestCode == 3){
            try {
                if (resCode == 200) {
                    Toast.makeText(getActivity(), "Address Added Successfully", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                if (resCode == 200) {
                    JSONObject jsonObject = new JSONObject(result);
                    if (resCode == 200) {
                        JSONObject output = jsonObject.getJSONObject("output");
                        JSONArray jsonArray = output.getJSONArray("data");
                        JSONObject data = jsonArray.optJSONObject(0);
                        et_street.setText(data.optString("ship_street"));
                        et_floorno.setText(data.optString("ship_floor"));
                        et_buildingno.setText(data.optString("ship_building"));
                        et_block.setText(data.optString("ship_block"));
                        et_avenue.setText(data.optString("ship_avenue"));
                        et_flatno.setText(data.optString("ship_appartment"));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    class GovernateClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            govID = governateList.get(position).getId();
            areaList.clear();
            getAreas(governateList.get(position).getArea());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class AreaClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
         areaID = areaList.get(position).getId();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
package com.example.art_stationary.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.example.art_stationary.Utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class UpdateProfileFragment extends Fragment implements ServiceResponse {

    EditText et_name,et_email,et_mobile;
    ConstraintLayout toolbar,img_back;
    Button button_update;


    public UpdateProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_update_profile, container, false);
        et_name = v.findViewById(R.id.et_name);
        et_email = v.findViewById(R.id.et_email);
        et_mobile = v.findViewById(R.id.et_mobile);
        toolbar = v.findViewById(R.id.toolbar);
        img_back = v.findViewById(R.id.img_back);
        button_update = v.findViewById(R.id.button_update);

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (et_name.getText().toString().isEmpty() || et_mobile.getText().toString().isEmpty()){
                     Toast.makeText(getActivity(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
                 }else {
                     updateProfile();
                 }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return v;
    }

    private void updateProfile(){
        String userId = PreferenceHelper.getInstance(getActivity()).getid();
        List<MultipartBody.Part> data = new ArrayList<>();
        data.add(MultipartBody.Part.createFormData("loginid",userId));
        data.add(MultipartBody.Part.createFormData("name",et_name.getText().toString()));
        data.add(MultipartBody.Part.createFormData("email",et_email.getText().toString()));
        data.add(MultipartBody.Part.createFormData("mobile",et_mobile.getText().toString()));
        new RetrofitService(getActivity(), ServiceUrls.UPDATEPROFILE, 2, 1, data, this)
                .callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
           if (requestCode == 1){
               try {
                   if (resCode == 200){
                       Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                       getActivity().onBackPressed();
                   }
               } catch (Exception e){
                   Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
               }
           }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
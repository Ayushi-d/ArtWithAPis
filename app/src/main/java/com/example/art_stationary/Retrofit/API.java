package com.example.art_stationary.Retrofit;


import com.example.art_stationary.Model.Categories.Example;
import com.example.art_stationary.Model.GetproductbyidModel.GetproductbyidExample;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    @GET("artbookstore/categories")
    Call<Example> getCategories();

    @Multipart
    @POST("artbookstore/getproductslist")
    Call<GetproductbyidExample>getcategoryByid(@Part MultipartBody.Part id);
}

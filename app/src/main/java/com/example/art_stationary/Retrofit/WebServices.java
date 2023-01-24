package com.example.art_stationary.Retrofit;

import com.example.art_stationary.Model.Categories.Example;
import com.example.art_stationary.Model.GetproductbyidModel.GetproductbyidExample;
import com.example.art_stationary.Utils.Handler.GetCategoriesHandler;
import com.example.art_stationary.Utils.Handler.GetproductByidHandler;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    public static final String BASE_URL = "http://kuwaitgate.com/artbookstore/webservices/";
    private static Retrofit retrofit = null;
    private static WebServices mInstance;
    private API api;


    public WebServices() {
        mInstance = this;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        api = retrofit.create(API.class);

    }

    public static WebServices getmInstance() {
        return mInstance;
    }

    public void getcategories(final GetCategoriesHandler loginHandler) {
        api.getCategories().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body());
                } else {
//                    try {
////                        JSONObject jsonObject = new JSONObject(responceData);
////                        String message = jsonObject.optString("message");
////                        loginHandler.onError(message);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                loginHandler.onError(t.getMessage());
            }
        });
    }

    public void getproductbyid(MultipartBody.Part id , final GetproductByidHandler getproductByidHandler) {

        api.getcategoryByid(id).enqueue(new Callback<GetproductbyidExample>() {
            @Override
            public void onResponse(Call<GetproductbyidExample> call, Response<GetproductbyidExample> response) {

                if (response.code() == 200) {
                    getproductByidHandler.onSuccess(response.body());
                } else {
//                    try {
////                        JSONObject jsonObject = new JSONObject(responceData);
////                        String message = jsonObject.optString("message");
////                        loginHandler.onError(message);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            @Override
            public void onFailure(Call<GetproductbyidExample> call, Throwable t) {
                getproductByidHandler.onError(t.getMessage());
            }
        });
    }

}

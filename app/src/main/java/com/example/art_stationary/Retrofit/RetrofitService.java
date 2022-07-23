package com.example.art_stationary.Retrofit;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.art_stationary.Utils.Gloabal_View;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private final String TAG = getClass().getName();

    private Context mContext;
    private String mtoken;
    private String mUrl;
    private List<MultipartBody.Part> imgPartList;
    private int mValue;
    private int mRequestCode;
    private Call<ResponseBody> mCall;
    private JsonObject body;
    private ServiceResponse mResponce;
    private MultipartBody.Part photoPart;
    private Map<String, RequestBody> mbody;
    private boolean forChat;


    //get
    public RetrofitService(Context mContext, String mUrl, int mValue, int mRequestCode, ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.mResponce = mResponce;

    }
   //gettoken
    public RetrofitService(Context mContext, String mUrl, String mtoken,int mValue, int mRequestCode, ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mtoken = mtoken;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.mResponce = mResponce;

    }
    //for post raw type
    public RetrofitService(Context mContext,  String mUrl, int mValue,
                           int mRequestCode,   JsonObject mBody,ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.mResponce = mResponce;
        this.body = mBody;

    }
     //raw with token
    public RetrofitService(Context mContext, String mUrl,String mtoken, int mValue, int mRequestCode, JsonObject body, ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mtoken = mtoken;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.body = body;
        this.mResponce = mResponce;
    }

    //body (header)
    public RetrofitService(Context mContext, String mtoken, String mUrl, int mValue,
                       int mRequestCode, Map<String, RequestBody> mbody, ServiceResponse mResponce) {
    this.mContext = mContext;
    this.mUrl = mUrl;
    this.mValue = mValue;
    this.mRequestCode = mRequestCode;
    this.mbody = mbody;
    this.mtoken = mtoken;
    this.mResponce = mResponce;
    }


    //for single img upload
    public RetrofitService(Context mContext, String mUrl, int mValue, int mRequestCode,
                           JsonObject mBody, MultipartBody.Part photoPart, ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.body = mBody;
        this.photoPart = photoPart;
        this.mResponce = mResponce;
    }

    //chat
    public RetrofitService(Context mContext, String mUrl, int mValue, int mRequestCode, JsonObject body, boolean forChat, ServiceResponse mResponce) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.mValue = mValue;
        this.mRequestCode = mRequestCode;
        this.mResponce = mResponce;
        this.body = body;
        this.forChat = forChat;
    }


    public void callService(boolean showProgress) {
       final Dialog dialog;
       dialog = Gloabal_View.showProgress(mContext);

        if (!isNetworkAvailable()) {
        Toast.makeText(mContext, "No Network available please check internet connection !", Toast.LENGTH_LONG).show();
        return;
    }

        if (showProgress) {
           //dialog.show();
        }
        //dialog.dismiss();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceUrls.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();



        ServiceClient retrofitApi = retrofit.create(ServiceClient.class);


        if (mValue == 1) {
            Log.d(TAG,  "///" + mUrl);
            mCall = retrofitApi.makeGetRequest( mUrl);
        }

        //single raw (post)
         else if (mValue == 2) {
            Log.d(TAG,  "///" + mUrl + "///" + body);
            mCall = retrofitApi.makePostRequest(mUrl, body);
        }

        else if (mValue == 3) {
            Log.d(TAG, "callService: requestcode 3");
            Log.d(TAG,  "///" + mUrl + "///" + body);
            mCall = retrofitApi.makePutRequest(/*"application/json",*/mUrl, body);
        }

        //raw with header and token (post)
       else if (mValue == 4)
        {
            Log.d(TAG, mtoken + "///" + mUrl + "///" +mbody);
            mCall = retrofitApi.makePostrawRequest(/*"application/x-www-form-urlencoded",*/ mtoken, mUrl, body);
        }

            else if (mValue == 5)
            {
                mCall = retrofitApi.makePostRequestWHeaderWitoutBody("application/x-www-form-urlencoded",
                        mtoken, mUrl);
            }
            //search,view agent//
            else if (mValue == 6)
            {
                Log.d(TAG, mtoken + "///" + mUrl + "///" + body + "///" + photoPart);
                mCall = retrofitApi.makeSingleImgUpload(mUrl,/*,mBody,*/ photoPart);
            }
            else if (mValue == 7)
            {
                mCall = retrofitApi.makeGetRequestheader("application/x-www-form-urlencoded", mUrl, mtoken, body);
            }else if (mValue ==8){
                mCall = retrofitApi.makeSingeImgUpload("",mUrl,body,photoPart);
            }

           else if (mValue == 9) {
            Log.d(TAG,  "///" + mUrl);
            mCall = retrofitApi.makeGetRequesttoekn( mUrl,mtoken);
           }

           else if (mValue ==10){
             mCall = retrofitApi.makedeleterequest(mUrl);
           }



            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    Log.d(TAG, "responceCode" + response.code());
                    //if (dialog.isShowing()) dialog.cancel();


                       if (response.isSuccessful()) {
                         try {
                            if (dialog.isShowing()) {
                               dialog.cancel();
                       }

                       String res = response.body().string();
                       Log.d(TAG, "responce " + res + "");
                       mResponce.onServiceResponse(res, mRequestCode, response.code());
                       } catch (Exception e) {
                       e.printStackTrace();
                        }
                        } else {
                        if (dialog.isShowing()) {
                             dialog.cancel();
                        }

                     }

                    String res, error = "";

                    if (response.code() == Gloabal_View.CODE_UNAUTHORISED) {
                        try {
                            error = response.errorBody().string();
                            mResponce.onServiceError(error, mRequestCode, response.code());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (response.code() == Gloabal_View.CODE_SUCCESSFULL) {
                        try {

                            res = response.body().string();
                            mResponce.onServiceResponse(res, mRequestCode, response.code());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            error = response.errorBody().string();
                            mResponce.onServiceError(error, mRequestCode, response.code());
                            JSONObject jsonObject = new JSONObject(error);
                            Log.d(TAG, "error=" + response.errorBody().string() + "//");
                            Log.d(TAG, "errorCode=" + jsonObject.optString("message") + "//");
                            Toast.makeText(mContext, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "failure//" + t.getMessage() + "//" + call);
                    //if (dialog.isShowing()) dialog.cancel();
                }
            });
        }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
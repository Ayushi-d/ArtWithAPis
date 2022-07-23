package com.example.art_stationary.Retrofit;

public interface ServiceResponse {
    void onServiceResponse(String result, int requestCode, int resCode);
   // void onServiceResponce(String result, int requestCode);
    void onServiceError(String error, int requestCode, int resCode );

}

package com.example.art_stationary.Retrofit;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;


public interface ServiceClient {

    @GET
    Call<ResponseBody> makeGetRequest( @Url String url);

    @GET
    Call<ResponseBody> makeGetRequesttoekn( @Url String url,@Header("x-access-token") String token);


    @POST
    Call<ResponseBody> makePostRequest(@Url String url, @Body JsonObject body);

    @POST
    Call<ResponseBody> makePostrawRequest(/*@Header("Content-Type") String header,*/@Header("x-access-token") String token,@Url String url, @Body JsonObject body);

    @PUT
    Call<ResponseBody> makePutRequest(@Url String url, @Body JsonObject body);


    @Multipart
    @PUT
    Call<ResponseBody> makeSingeImgUpload(/*@Header("Content-Type") String header,*/@Header("token") String token, @Url String url, @Body JsonObject body , @Part MultipartBody.Part photo);

    @POST
    Call<ResponseBody> makePostRequestWithUrlEncoded(@Header("Content-Type") String header,/*@Header("X-Requested-With") String contentType,*/@Url String url,@Body JsonObject body);

    @POST
    @Multipart
    Call<ResponseBody> makePostWTokenRequest(@Header("token") String token, @Url String url, @PartMap JsonObject object);

    @Multipart
    @POST
    Call<ResponseBody> makeMultipleImgUpload(@Url String url, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> imagesList);



    @POST
    @Multipart
    Call<ResponseBody> makePostRequestWHeader(@Header("Content-Type") String header,@Header("token") String token ,@Url String url, @PartMap Map<String, RequestBody> body);


    @POST
    Call<ResponseBody> makePostRequestWHeaderWitoutBody(@Header("Content-Type") String header, @Header("token") String token, @Url String url);


    @Multipart
    @PUT
    Call<ResponseBody> makeSingleImgUpload(@Url String url/*, @PartMap JsonObject object*/,@Part MultipartBody.Part photo);


    @GET
    @Multipart
    Call<ResponseBody> makeGetRequestheader(@Header("Content-Type") String header, @Url String mUrl, @Header("token") String mtoken, @PartMap JsonObject object);

    @POST
    Call<ResponseBody> uploadRawDataRequest(@Header("Content-Type") String header, @Url String mUrl,@Body JsonObject jsonObject);

    @DELETE
    Call<ResponseBody> makedeleterequest( @Url String url);
}
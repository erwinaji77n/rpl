package com.justfriend.suratuin.retrofit;

import com.justfriend.suratuin.Model.Login.LoginUser;
import com.justfriend.suratuin.Model.Login.ResponseLogin;
import com.justfriend.suratuin.Model.andy.Response;
import com.justfriend.suratuin.Model.outbox.ResponseOutbox;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    @POST("auth/signin")
    Call<ResponseLogin> getLogin(@Body LoginUser loginUser);

//    @FormUrlEncoded
//    @POST("auth/signin")
//    Call<ResponseLogin> getLogin(
//            @Field("nim") String nim,
//            @Field("password") String password
//    );

    @GET("index.php/C_json/select_sehat")
    Call<Response> getDataku();

    @GET("outbox")
    Call<ResponseOutbox> getOutbox();

}

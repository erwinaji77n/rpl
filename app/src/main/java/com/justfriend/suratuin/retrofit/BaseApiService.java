package com.justfriend.suratuin.retrofit;

import com.justfriend.suratuin.Model.Login.LoginUser;
import com.justfriend.suratuin.Model.Login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseApiService {

    @POST("auth/signin")
    Call<ResponseLogin> getLogin(@Body LoginUser loginUser);

}

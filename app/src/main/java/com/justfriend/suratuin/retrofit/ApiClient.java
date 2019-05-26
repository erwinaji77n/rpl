package com.justfriend.suratuin.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://surat.arilukmanp.com/api/";
    public static final String BASE_URL_ANDY = "http://andy.lauwba.com/";

    public static BaseApiService client(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(BaseApiService.class);
    }

    public static BaseApiService client2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_ANDY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttp())
                .build();
        return retrofit.create(BaseApiService.class);
    }
    public static OkHttpClient getOkHttp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC).
                setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

}

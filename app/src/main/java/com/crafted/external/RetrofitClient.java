package com.crafted.external;

import com.crafted.models.ticket_info_model;
import com.crafted.models.ticket_model;
import com.crafted.models.user_profile_model;
import com.crafted.retrofit_interfaces.*;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static String accessToken;
    private static String BASE_URL = "https://crafted.gtsg-clan.de/api/v1/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getBearerToken(){return accessToken;}

    public static void setAccessToken(String accessToken) {
        RetrofitClient.accessToken = "Bearer "+ accessToken;


    }


}

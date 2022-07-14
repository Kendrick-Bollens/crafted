package com.crafted.retrofit_interfaces;

import com.crafted.models.user_profile_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface user_profile_interface {

    @GET("users/profiles")
    public Call<List<user_profile_model>> getUsers(@Header("Authorization") String authHeader);

    @GET("users/profiles")
    public Call<List<user_profile_model>> getUsers(@Header("Authorization") String authHeader, @Query("searchTerm") String search);


    @GET("users/profiles/{userid}")
    public Call<List<user_profile_model>> getUserById(@Header("Authorization") String authHeader, @Path("userid") int id);

    @GET("users/profiles/my")
    public Call<user_profile_model> getMyUser(@Header("Authorization") String authHeader);

}

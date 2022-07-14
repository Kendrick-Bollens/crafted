package com.crafted.retrofit_interfaces;

import com.crafted.models.image_model;
import com.crafted.models.ticket_info_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface image_interface {

    @GET("images/{userid}")
    public Call<image_model> getImageById(@Header("Authorization") String authHeader, @Path("userid") int id);

}

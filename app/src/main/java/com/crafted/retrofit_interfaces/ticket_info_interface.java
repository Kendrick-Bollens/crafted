package com.crafted.retrofit_interfaces;

import com.crafted.models.ticket_info_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ticket_info_interface {

    @GET("users/profiles")
    public Call<List<ticket_info_model>> getTickets(@Header("Authorization") String authHeader);

    @GET("users/profiles/{userid}")
    public Call<List<ticket_info_model>> getTicketById(@Header("Authorization") String authHeader, @Path("userid") int id);

    @GET("users/profiles/my")
    public Call<ticket_info_model> getMyTickets(@Header("Authorization") String authHeader);

}

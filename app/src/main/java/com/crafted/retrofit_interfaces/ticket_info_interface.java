package com.crafted.retrofit_interfaces;

import com.crafted.models.ticket_info_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ticket_info_interface {

    @GET("tickets/")
    public Call<List<ticket_info_model>> getTickets(@Header("Authorization") String authHeader);

    @GET("tickets/")
    public Call<List<ticket_info_model>> getTickets(@Header("Authorization") String authHeader, @Query("search term") String searchterm);

    @GET("tickets/{ticketid}")
    public Call<List<ticket_info_model>> getTicketById(@Header("Authorization") String authHeader, @Path("ticketId") int id);

}
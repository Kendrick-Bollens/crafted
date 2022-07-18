package com.crafted.retrofit_interfaces;

import com.crafted.models.ticket_info_model;
import com.crafted.models.ticket_post_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ticket_info_interface {

    @GET("tickets/")
    public Call<List<ticket_info_model>> getTickets(@Header("Authorization") String authHeader);

    @GET("tickets/")
    public Call<List<ticket_info_model>> getTickets(@Header("Authorization") String authHeader, @Query("search term") String searchterm);

    @GET("tickets/{ticketId}")
    public Call<ticket_info_model> getTicketById(@Header("Authorization") String authHeader, @Path("ticketId") int id);

    @POST("tickets/")
    public Call<ticket_info_model> createTicket(@Header("Authorization") String authHeader, @Body ticket_post_model model);

}

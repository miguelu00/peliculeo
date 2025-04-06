package com.miguelu00.peliculeo_android.servicioAPI;

import com.miguelu00.peliculeo_android.models.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TicketAPIServicio {

    @POST("tickets/add")
    Call<String> addTicket(@Body Ticket ticket);

    @GET("tickets/")
    Call<List<Ticket>> getAllTickets();

    @GET("tickets/{id}")
    Call<Ticket> getTicketById(@Path("id") int id);

    @GET("tickets/for/{nifusuario}")
    Call<List<Ticket>> getTicketsByUser(@Path("nifusuario") String nifUsuario);

    @PUT("tickets/ticket/{id}")
    Call<String> updateTicket(@Path("id") int id, @Body Ticket ticket);

    @DELETE("tickets/{id}")
    Call<String> deleteTicket(@Path("id") int id);

    @HTTP(method = "DELETE", path = "tickets/uno", hasBody = true)
    Call<String> deleteTicketUnaPeli(@Body Ticket ticket);
}
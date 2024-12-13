package com.miguelu00.peliculeo_android.servicioAPI;

import com.miguelu00.peliculeo_android.models.Cliente;
import com.miguelu00.peliculeo_android.models.JsonRespuesta;
import com.miguelu00.peliculeo_android.models.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PeliculaAPIServicio {

    @POST("/auth/register")
    Call<JsonRespuesta> registro(@Body Cliente cliente);

    @POST("/auth/login")
    Call<JsonRespuesta> login(@Body Cliente cliente);

    @GET("peliculas")
    Call<List<Pelicula>> getPeliculas();

    @GET("peliculas/{cod}")
    Call<Pelicula> getPeliculaByCod(@Path("cod") int cod);

    @GET("peliculas/poster/{cod}")
    Call<Pelicula> getPosterByCodPelicula(@Path("cod") int cod);
}

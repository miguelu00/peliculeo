package com.miguelu00.peliculeoMobile.servicioAPI;

import com.miguelu00.peliculeoMobile.models.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculaAPIServicio {

    @GET("peliculas")
    Call<List<Pelicula>> getPeliculas();

    @GET("pelicula/{cod}")
    Call<Pelicula> getPeliculaByCod(@Path("cod") int cod);
}

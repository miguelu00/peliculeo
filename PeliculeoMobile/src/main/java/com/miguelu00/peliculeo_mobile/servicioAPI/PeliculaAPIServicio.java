package com.miguelu00.peliculeo_mobile.servicioAPI;

import com.miguelu00.peliculeo_mobile.models.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PeliculaAPIServicio {

    @GET("peliculas")
    Call<List<Pelicula>> getPeliculas();

    @GET("peliculas/{cod}")
    Call<Pelicula> getPeliculaByCod(@Path("cod") int cod);

    @GET("peliculas/poster/{cod}")
    Call<Pelicula> getPosterByCodPelicula(@Path("cod") int cod);
}

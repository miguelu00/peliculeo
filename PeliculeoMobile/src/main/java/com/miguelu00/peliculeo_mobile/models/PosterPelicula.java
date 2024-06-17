package com.miguelu00.peliculeo_mobile.models;

public class PosterPelicula {

    private int codPelicula;
    private String urlPoster;

    public PosterPelicula() {
    }

    public PosterPelicula(int codPelicula, String urlPoster) {
        this.codPelicula = codPelicula;
        this.urlPoster = urlPoster;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }
}

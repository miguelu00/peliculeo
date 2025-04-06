package com.miguelu00.peliculeo_android.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PosterPelicula {

    private int codPelicula;
    private String titulo;      // titulo de la película

    @SerializedName("img")
    private String blobPoster;  // img en formato BLOB (string Base64 de bytes)

    public PosterPelicula() {
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public String getBlobPoster() {
        return blobPoster;
    }

    public Bitmap getBlobPosterAsBitmap() {
        if (blobPoster != null && !blobPoster.isEmpty()) {
            byte[] decodedBytes = Base64.decode(blobPoster, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
        return null;
    }

    public String getTitulo() { return titulo; }

    @Override
    public String toString() {
        if (blobPoster == null) {
            blobPoster = "123456789101112";
        }
        return "Pelicula{" +
                ", titulo='" + titulo + '\'' +
                "codPelicula=" + codPelicula +
                ", blobPoster=" + blobPoster.substring(1, 15) + "..." +
                '}';
    }
}

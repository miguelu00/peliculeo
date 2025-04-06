package com.miguelu00.peliculeo_android.models;

import lombok.Data;

@Data
public class Pelicula {

    private int codPelicula;
    private String titulo;

    private String fechaEstreno;

    private String genero;

    private int anio;
    private String URLimg;

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getURLimg() {
        return this.URLimg;
    }
    public void setURLimg(String urlImg) {
        this.URLimg = urlImg;
    }
}

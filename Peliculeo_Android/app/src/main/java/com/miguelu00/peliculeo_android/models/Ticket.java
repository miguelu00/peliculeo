package com.miguelu00.peliculeo_android.models;

import lombok.Data;

public class Ticket {

    private int ID;
    private String NIF_cliente;
    private int codPelicula;
    private String fecha_Peli;

    public Ticket() {

    }

    public Ticket(int ID, String nif_Cliente, int codPelicula, String fecha_Peli) {
        this.ID = ID;
        this.NIF_cliente = nif_Cliente;
        this.codPelicula = codPelicula;
        this.fecha_Peli = fecha_Peli;
    }

    public int getID() {
        return ID;
    }

    public String getNIF_cliente() {
        return NIF_cliente;
    }

    public void setNIF_cliente(String NIF_cliente) {
        this.NIF_cliente = NIF_cliente;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getFecha_Peli() {
        return fecha_Peli;
    }

    public void setFecha_Peli(String fecha_Peli) {
        this.fecha_Peli = fecha_Peli;
    }
}

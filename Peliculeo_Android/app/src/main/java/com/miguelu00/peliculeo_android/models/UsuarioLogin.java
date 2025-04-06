package com.miguelu00.peliculeo_android.models;

public class UsuarioLogin {

    private String nif;
    private String nombre;
    private String apellidos;
    private String fechAlta;
    private String provincia;
    private String password;

    public UsuarioLogin(String email, String pwd) {
        this.nif = email;
        this.password = pwd;
        this.nombre = "";
        this.apellidos = "";
        this.fechAlta = "";
        this.provincia = "";
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_Alta() {
        return fechAlta;
    }

    public void setFecha_Alta(String fecha_Alta) {
        this.fechAlta = fecha_Alta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

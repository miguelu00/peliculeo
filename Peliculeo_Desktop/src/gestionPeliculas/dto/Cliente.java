/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Miguelañez-PC
 */

//Hacemos la clase que queremos guardar/recoger del fichero BBDD SERIALIZABLE
public class Cliente implements Serializable {
    private String DNI;
    private String nombre;
    private String apellidos;
    private String fechAlta;
    private String provincia;
    
    public Cliente() {
        this.DNI = "";
        this.nombre = "";
        this.apellidos = "";
        this.fechAlta = UtilesFecha.dateToStr(new Date());
        this.provincia = "";
    }
    
    public Cliente(String nombre, String apellidos, String fechAlta, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechAlta = fechAlta;
        this.provincia = provincia;
    }

    public Cliente(String DNI, String nombre, String apellidos, String fechAlta, String provincia) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechAlta = UtilesFecha.dateToStr(new Date());
        this.provincia = provincia;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getFechAlta() {
        return fechAlta;
    }

    public void setFechAlta(String fechAlta) {
        this.fechAlta = fechAlta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    /**
     * Actualiza desde un ARRAYLIST los datos al objeto Cliente actual.
     * @param datosComoList Un objeto ArrayList<String> que deberá contener, en orden, los datos del Cliente que estamos editando.
     */
    public void setArray(ArrayList<String> datosComoList) {
        this.DNI = datosComoList.get(0);
        this.nombre = datosComoList.get(1);
        this.apellidos = datosComoList.get(2);
        this.fechAlta = datosComoList.get(3);
        this.provincia = datosComoList.get(4);
    }

    @Override
    public String toString() {
        return "Cliente{" + "DNI=" + this.DNI + "; nombre=" + nombre + ", apellidos=" + apellidos + ", fechAlta=" + fechAlta + ", provincia=" + provincia + '}';
    }
    
    public String toStringLista() {
        return this.DNI + "; " + nombre + ", " + apellidos;
    }
    
    public String[] toArrayString() {
        return new String[]{this.getDNI(), this.getNombre(), this.getApellidos(), this.getFechAlta(), this.getProvincia()};
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  TODO -> Ingresar método (dev. Boolean) para comprobar si el usuario seleccionado en la lista de gestión es el nuestro propio.
 * @author Miguelañez-PC
 */

public class Cliente implements Serializable {
    
    @SerializedName("nif")
    private String NIF; // ejemplo: 12345678L
    private String nombre;
    private String apellidos;   
    private String password;
    
    @SerializedName("fecha_Alta")
    private String fechAlta;
    
    private String provincia;
    
    public Cliente() {
        this.NIF = "";
        this.nombre = "";
        this.apellidos = "";
        this.password = "";
        this.fechAlta = UtilesFecha.dateToStr(new Date());
        this.provincia = "";
    }
    
    public Cliente(String nombre, String apellidos, String fechAlta, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechAlta = fechAlta;
        this.provincia = provincia;
    }

    public Cliente(String DNI, String nombre, String apellidos, String password, String fechAlta, String provincia) {
        this.NIF = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.fechAlta = UtilesFecha.dateToStr(new Date());
        this.provincia = provincia;
    }

    public Cliente(String DNI, String nombre, String apellidos, String fechAlta, String provincia) {
        this.NIF = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechAlta = UtilesFecha.dateToStr(new Date());
        this.provincia = provincia;
    }

    public String getDNI() {
        return NIF;
    }

    public void setDNI(String DNI) {
        this.NIF = DNI;
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
    
    public void setPassword(String passwd) {
        this.password = passwd;
    }
    
    /**
     * Actualiza desde un ARRAYLIST los datos al objeto Cliente actual.
     * @param datosComoList Un objeto ArrayList<String> que deberá contener, en orden, los datos del Cliente que estamos editando.
     */
    public void setArray(ArrayList<String> datosComoList) {
        this.NIF = datosComoList.get(0);
        this.nombre = datosComoList.get(1);
        this.apellidos = datosComoList.get(2);
        this.fechAlta = datosComoList.get(3);
        this.provincia = datosComoList.get(4);
    }

    @Override
    public String toString() {
        return this.NIF + " --> nombre=" + nombre + ", apellidos=" + apellidos + ", fechAlta=" + fechAlta + ", provincia=" + provincia + '}';
    }
    
    public String toStringLista() {
        return this.NIF + "; " + nombre + ", " + apellidos;
    }
    
    /**
     * 
     * Métodos para SERIALIZAR / DESERIALIZAR un String JSON a objeto Cliente y viceversa
    **/
    public String toJSONString() {
        return String.format(
            "{\"nif\": \"%s\", \"nombre\": \"%s\", \"apellidos\": \"%s\", \"fechAlta\": \"%s\", \"provincia\": \"%s\", \"password\": \"%s\"}",
            this.NIF,
            this.nombre,
            this.apellidos,
            this.fechAlta,
            this.provincia,
            this.password
        );
    }
    
    /**
     * 
     * Métodos para SERIALIZAR / DESERIALIZAR un String JSON a objeto Cliente y viceversa
    **/
    public static Cliente getClienteFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoCliente = new TypeToken<Cliente>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoCliente);
        } catch (Exception err) {
            return null;
        }
    }
    
    /**
     * La clase Type ayuda a la librería Gson a 'traducir' los objetos
     * que se encuentren en el interior del array JSON consumido a la referencia
     * de tipos que use el ArrayList especificado.
     * @param jsonString
     * @return 
     */
    public static List<Cliente> getArrayClientesFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoListaClientes = new TypeToken<List<Cliente>>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoListaClientes);
        } catch (Exception err) {
            return null;
        }
    }
    
    public String[] toArrayString() {
        return new String[]{this.getDNI(), this.getNombre(), this.getApellidos(), this.getFechAlta(), this.getProvincia()};
    }
    
}

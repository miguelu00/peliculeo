package gestionPeliculas.dto;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * CLASE CON MÉTODOS PARA HACER MAS FLUIDA LA PROGRAMACIÓN DE LA APP
 * Generalmente, métodos para agilizar el Input/Output de datos desde Java @ Otras aplicaciones
 * 
 * @author Miguelañez-PC
 */
public class Utiles {
    
    /**
     * Devuelve la fecha de Alta de este usuario, en formato DD/MM/AAAA
     * @return Un String que representa la Fecha de Alta (formato DD/MM/AAAA)
     */
    public static String getFechaFormateada(Date fechaSinFormato) {
        SimpleDateFormat formateoFecha = new SimpleDateFormat("dd/mm/yyyy");
        return formateoFecha.format(fechaSinFormato);
    }
    
    /**
     * Devuelve un objeto Date a partir de una fecha en String
     * @param fechaFormateada Cadena representando una fecha (debe ser en formato DD/MM/AAAA)
     * @return la fecha en formato java.util.Date
     */
    public static Date getDateDesdeString(String fechaFormateada) {
        DateFormat formatear = new SimpleDateFormat("dd/mm/yyyy");
        try {
            return formatear.parse(fechaFormateada);
        } catch (ParseException fechaErr) {
            System.err.println("Error en el formateo de Fecha");
        }
        return null;
    }
}

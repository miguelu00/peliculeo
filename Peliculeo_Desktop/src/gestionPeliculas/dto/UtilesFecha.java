/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Miguelañez-PC
 */
public class UtilesFecha {
    
    /**
     * Transformar una fecha formateada en String (FORMATO: dd/MM/yyyy HH:mm)
     * @param fechaEnStr la fecha, directamente cogida por método automatizado (un jSpinner)
     * @return La fecha formateada EN FORMATO STRING, ó bien NULL si no se ha introducido una fecha correcta.
     */
    public static String spinnerFechaToStringDate(Object fechaEnStr) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        String formateada = sdf.format(fechaEnStr);
        
        return formateada;
    }
    
    /**
     * Recoge un STRING (formateado previamente con SimpleDateFormat) y lo transforma a objeto java.Util.DATE (mediante parse())
     * @param fechaFormateada la fecha formateada con SDF (en String)
     * @return el objeto DATE si se consigue el formateo, NULL en caso contrario.
     */
    public static Date fechaStrToDate(String fechaFormateada) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            return sdf.parse(fechaFormateada);
        } catch (ParseException parsExc) {
            System.err.println("FALLO al formatear la fecha!");
            return null;
        }
    }
    
    /**
     * Recoge un objeto java.util.DATE, y lo transforma a String (Formato dd/MM/yy HH:mm)
     * @param fechaSinFormato
     * @return 
     */
    public static String dateToStr(Date fechaSinFormato) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        
        return sdf.format(fechaSinFormato);
    }
}

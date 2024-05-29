/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class PeliculaNoInsertadaException extends Exception {
    public PeliculaNoInsertadaException() {
        super("SE HA PRODUCIDO UN ERROR AL INSERTAR LA PELICULA. CHEQUEE SU CONEXION A LA BASE DE DATOS!");
    }
    
    public PeliculaNoInsertadaException(String msg) {
        super(msg);
    }
}

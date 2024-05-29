/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class ErrorInsercionException extends Exception {
    public ErrorInsercionException() {
        super("ERROR AL INSERTAR O HACER UNA OPERACION EN BBDD! Comprueba tu conexion a la Base de Datos e intentalo de nuevo!");
        System.err.println("ERROR GENERICO EN BBDD.");
    }
    
    public ErrorInsercionException(String msg) {
        super(msg);
    }
}

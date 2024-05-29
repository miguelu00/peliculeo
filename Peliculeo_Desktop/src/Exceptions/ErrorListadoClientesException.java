/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class ErrorListadoClientesException extends Exception {
    public ErrorListadoClientesException() {
        super("SE HA PRODUCIDO UN ERROR AL LISTAR LOS CLIENTES A TRAVES DE LA BBDD!");
    }
    
    public ErrorListadoClientesException(String msg) {
        super(msg);
    }
}

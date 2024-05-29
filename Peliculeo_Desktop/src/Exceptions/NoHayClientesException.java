/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class NoHayClientesException extends Exception {
    public NoHayClientesException() {
        super("ERROR! No hay clientes en el Sistema!");
    }
    
    public NoHayClientesException(String msg) {
        super(msg);
    }
}

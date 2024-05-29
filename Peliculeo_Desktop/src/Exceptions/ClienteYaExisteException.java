/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class ClienteYaExisteException extends Exception {
    public ClienteYaExisteException() {
        super("EL NIF PARA EL CLIENTE INTRODUCIDO YA SE ENCUENTRA EN EL SISTEMA!");
    }
    
    public ClienteYaExisteException(String msg) {
        super(msg);
    }
}

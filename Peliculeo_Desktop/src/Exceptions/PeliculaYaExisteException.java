/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author Miguelañez-PC
 */
public class PeliculaYaExisteException extends Exception {
    public PeliculaYaExisteException() {
        super("""
              La pelicula que se ha intentado introducir YA EXISTE!
              Pruebe con otra pelicula, o elimine la ya existente...""");
    }
}

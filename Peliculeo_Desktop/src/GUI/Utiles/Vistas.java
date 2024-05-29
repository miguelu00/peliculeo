/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Utiles;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 * JAVA - SWING
 * 
 * CLASE CON MÉTODOS PARA HACER MAS FLUIDA LA PROGRAMACIÓN DE LA GUI
 * Generalmente, métodos para agilizar la programación del Input/Output de datos desde Java @ Usuario Final
 * @author Miguelañez-PC
 */
public class Vistas {
    
    public static void mostrarErrorGUI(JRootPane rootPane, String titulo, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    public static String mostrarInputGUI(JRootPane rootPane, String msg) {
        return JOptionPane.showInputDialog(rootPane, msg);
    }
    
    public static String mostrarInputGUI(String msg) {
        return JOptionPane.showInputDialog(msg);
    }
    
    public static void mostrarMensajeGUI(JRootPane rootPane, String titulo, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}

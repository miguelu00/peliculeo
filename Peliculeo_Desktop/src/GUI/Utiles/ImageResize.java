/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Utiles;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Miguelañez-PC
 */
public class ImageResize {
    
    /**
     * Método para insertar una imágen en un JLabel, de forma que se redimensione automáticamente
     * @param label1 El JLabel al que queremos añadir la imágen
     * @param imgRoot La ruta <b>(ABSOLUTA)</b> de la imágen
     */
    public static void setImageLabel(JLabel label1, String imgRoot) {
        ImageIcon image = new ImageIcon(imgRoot);
        if (image.getImage() == null) {
            System.err.println("NO SE HA PODIDO ENCONTRAR UNA O MAS IMAGENES PARA LA INTERFAZ!"
                    + "\n"
                    + "Ruta buscada: " + imgRoot);
            return;
        }
        Icon icon = new ImageIcon(image.getImage()
                .getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_DEFAULT));
        
        label1.setIcon(icon);
        label1.repaint();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Utiles;

import java.awt.event.MouseEvent;

/**
 *
 * @author Miguelañez-PC
 */
public interface ArrastreListener {
    
    /**
     * Método para indicarle qué hacer al elemento que implemente este listener al arrastrar el ratón.
     */
    public void onArrastre(MouseEvent e);
    
    public void onDblClick(MouseEvent e);
}

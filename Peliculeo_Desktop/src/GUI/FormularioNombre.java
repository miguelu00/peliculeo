/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Miguelañez-PC
 */
public class FormularioNombre extends JPanel {
    private JLabel labelNombre;
    private JTextField campoTexto;
    private JButton btnContinuar;
    
    public FormularioNombre() {
        //Layout de este JPanel
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        this.setMinimumSize(new Dimension(250, 100));
        this.setMaximumSize(new Dimension(400, 400));
        this.labelNombre = new JLabel("Pelicula: ");
        this.campoTexto = new JTextField();
        this.campoTexto.setMaximumSize(new Dimension(250, 70));
        this.campoTexto.setMinimumSize(new Dimension(100, 70));
        this.campoTexto.setPreferredSize(new Dimension(150, 70));
        
        this.btnContinuar = new JButton("INICIAR TEMP.");
        this.btnContinuar.setMinimumSize(new Dimension(150, 60));
        this.btnContinuar.setMaximumSize(new Dimension(150, 60));
        
        
        this.labelNombre.setLabelFor(this.campoTexto);
        //Agregar los listeners para comprobar               
        this.btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (campoTexto.getText().length() > 20) {
                    System.err.println("Máximo 20 caracteres!");
                    JOptionPane.showMessageDialog(FormularioNombre.this, "Máximo 20 caracteres!", "ERROR Entrada", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!campoTexto.getText().matches("[A-Z a-z ]+")) {
                    System.err.println("Solo admite alfabéticos!");
                    JOptionPane.showMessageDialog(FormularioNombre.this, "Sólo admite alfabéticos!", "ERROR Entrada", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        
        this.btnContinuar.setVisible(true);
        
        this.add(this.btnContinuar);
        this.add(this.labelNombre);
        this.add(this.campoTexto);
    }
    
    public JLabel getLabelNombre() {
        return this.labelNombre;
    }
    
    public JTextField getCampoTexto() {
        return this.campoTexto;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
    
    public void setActionListenerBtnContinuar(ActionListener callback) {
        this.btnContinuar.addActionListener(callback);
    }

    public void setBtnContinuar(JButton btnContinuar) {
        this.btnContinuar = btnContinuar;
    }
}

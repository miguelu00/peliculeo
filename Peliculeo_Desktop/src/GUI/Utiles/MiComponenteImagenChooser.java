/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Utiles;

import gui_editorpersonalizado.ImagenFondo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Miguelañez-PC
 */
public class MiComponenteImagenChooser extends JPanel implements Serializable, MouseListener {
    private File fondo;
    private String tituloPeli;
    private JLabel vistaTitulo;
    private ImagenFondo fondoImg;
    private ArrastreListener arrastreListener;
    static String RUTA_IMG_DEFAULT = "src/GUI/imgs/placeholder-img.png";
    
    //ATRIBUTOS NECESARIOS PARA ArrastreLISTENER
    private Point posicionCursor;
    private boolean ratonPresionado = false;
    private static final int MIN_PX_POSICION_ARRASTRE = 110;
    
    public MiComponenteImagenChooser() {
        this.tituloPeli = "";
        //Crear la vista (JLabel) para el título de la peli
        this.vistaTitulo = new JLabel(tituloPeli);
        vistaTitulo.setForeground(Color.BLACK);
        vistaTitulo.setBackground(Color.LIGHT_GRAY);
        vistaTitulo.setFont(new Font("Source Sans Pro", Font.BOLD, 10));
        vistaTitulo.setText(tituloPeli);
        this.arrastreListener = new ArrastreListener() {
            @Override
            public void onArrastre(MouseEvent e) {
                System.err.println("ERROR! No se ha implementado el método onArrastre() de este componente."
                        + "\n"
                        + this.toString());
            }
            
            @Override
            public void onDblClick(MouseEvent e) {
                JFileChooser elegirFicherosWindow = new JFileChooser();
                int resultado = elegirFicherosWindow.showOpenDialog((JPanel) e.getSource());

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    //Al aceptar, cambiar el texto de la ruta por la ruta elegida
                    File ficheroTmp = elegirFicherosWindow.getSelectedFile();
                    vistaTitulo.setText(ficheroTmp.getAbsolutePath());
                    fondoImg.setImagen(ficheroTmp.getAbsoluteFile());
                }
                if (resultado == JFileChooser.CANCEL_OPTION) {
                    //No hacer nada al cancelar
                }
            }
        }; 
        this.fondo = new File(RUTA_IMG_DEFAULT);
        
        this.addMouseListener(this);
        this.add(this.vistaTitulo);
        
        this.setVisible(true);
    }
    public MiComponenteImagenChooser(File fondo, String tituloPeli) {
        this.fondo = fondo;
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK);
        JLabel vistaTituloPeli = new JLabel(tituloPeli);
        vistaTituloPeli.setForeground(Color.WHITE);
        vistaTituloPeli.setBackground(Color.GRAY);
        vistaTituloPeli.setFont(new Font("Source Sans Pro", Font.BOLD, 10));
        this.arrastreListener = new ArrastreListener() {
            @Override
            public void onArrastre(MouseEvent e) {
                System.err.println("ERROR! No se ha implementado el método onArrastre() de este componente."
                        + "\n"
                        + this.toString());
            }
            @Override
            public void onDblClick(MouseEvent e) {
                JFileChooser elegirFicherosWindow = new JFileChooser();
                int resultado = elegirFicherosWindow.showOpenDialog((JPanel) e.getSource());

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    //Al aceptar, cambiar el texto de la ruta por la ruta elegida
                    File ficheroTmp = elegirFicherosWindow.getSelectedFile();
                    vistaTitulo.setText(ficheroTmp.getAbsolutePath());
                    fondoImg.setImagen(ficheroTmp.getAbsoluteFile());
                }
                if (resultado == JFileChooser.CANCEL_OPTION) {
                    //No hacer nada al cancelar
                }
            }
        };
        this.addMouseListener(this);
        vistaTituloPeli.setText(tituloPeli);
        this.vistaTitulo = vistaTituloPeli;
        this.add(this.vistaTitulo);
        
        this.setVisible(true);
    }
    
    public void setRutaImagen(File fondoNuevo) {
        this.fondo = fondoNuevo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Comprobamos primero que la Ruta para el fichero de fondo existe y está declarada
        if (fondo != null && fondo.exists()) {
            ImageIcon imagen = new ImageIcon(fondo.getAbsolutePath());
            
            //Dibujar la imagen al fondo del componente
            g.drawImage(imagen.getImage(), 0, 0, null);
        } else {
            System.out.println("FONDO DE IMAGEN NO ENCONTRADO!");
        }
    }
    
    public File getFondo() {
        return fondo;
    }

    public void setFondo(File fondo) {
        this.fondo = fondo;
    }

    public String getTituloPeli() {
        return tituloPeli;
    }

    public void setTituloPeli(String tituloPeli) {
        this.tituloPeli = tituloPeli;
        this.vistaTitulo.setText(tituloPeli);
    }

    public String getRutaImg() {
        return fondoImg.getImagen().getAbsolutePath();
    }

    private void setRutaImg(String rutaImg) {
        this.fondoImg.setImagen(new File(rutaImg));
    }

    public ArrastreListener getArrastreListener() {
        return arrastreListener;
    }

    public void setArrastreListener(ArrastreListener arrastreListener) {
        this.arrastreListener = arrastreListener;
    }

    public static String getRUTA_IMG_DEFAULT() {
        return RUTA_IMG_DEFAULT;
    }

    public static void setRUTA_IMG_DEFAULT(String RUTA_IMG_DEFAULT) {
        MiComponenteImagenChooser.RUTA_IMG_DEFAULT = RUTA_IMG_DEFAULT;
    }

    /**
     * Al clickar el componente, se abrirá una ventana nueva con la opción de cambiar la imágen seleccionada para la
     * Película que se está tratando de crear.
     * 
     * TODO --> ABRIR VENTANA PARA ELEGIR IM�?GEN (File Picker ?? EST�? EN LA PR�?CTICA DE EDITOR PERS.) ;
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.arrastreListener.onDblClick(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        //Recoger el punto en el que se ha pulsado el botón del ratón
        posicionCursor = evt.getPoint();
        ratonPresionado = true;
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        //AL LEVANTAR EL BOTÓN DEL RATÓN. Comprobar si se ha movido el puntero un número de píxeles (en ejes X e Y)
        boolean menorPorX = evt.getPoint().getX() < (posicionCursor.getX() - MIN_PX_POSICION_ARRASTRE), 
                mayorPorX = evt.getPoint().getX() > (posicionCursor.getX() + MIN_PX_POSICION_ARRASTRE),
                menorPorY = evt.getPoint().getY() < (posicionCursor.getY() - MIN_PX_POSICION_ARRASTRE),
                mayorPorY = evt.getPoint().getY() > (posicionCursor.getY() + MIN_PX_POSICION_ARRASTRE);
        if (menorPorX || mayorPorX || menorPorY || mayorPorY) {
            arrastreListener.onArrastre(evt);
        }
        ratonPresionado = false;
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        this.setBackground(Color.GREEN);
        this.vistaTitulo.setForeground(Color.BLACK);
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        this.setBackground(Color.WHITE);
        this.vistaTitulo.setForeground(Color.WHITE);
    }

    @Override
    public String toString() {
        return "MiComponenteImagenChooser{" + "X= " + this.getX() + "| Y=" + this.getY() + "; fondo=" + fondo + ", tituloPeli=" + tituloPeli + ", vistaTitulo=" + vistaTitulo + ", arrastreListener=" + arrastreListener + '}';
    }
        
}

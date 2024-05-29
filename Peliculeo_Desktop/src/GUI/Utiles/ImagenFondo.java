/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Utiles;

/**
 *
 * @author Miguelañez-PC
 */
public class ImagenFondo {
    private String rutaImagen;
    private float opacidad;

    public ImagenFondo(String rutaImagen, float opacidad) {
        this.rutaImagen = rutaImagen;
        this.opacidad = opacidad;
    }

    public ImagenFondo() {
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public float getOpacidad() {
        return opacidad;
    }

    public void setOpacidad(float opacidad) {
        this.opacidad = opacidad;
    }
    
    
}

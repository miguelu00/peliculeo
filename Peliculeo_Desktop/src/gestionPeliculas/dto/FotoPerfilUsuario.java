/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import java.util.Base64;

/**
 *
 * @author Miguelañez-PC
 */
public class FotoPerfilUsuario {
    private String NIF;
    private byte[] imgPerfil;

    public FotoPerfilUsuario() {
    }

    public FotoPerfilUsuario(String NIF, byte[] imgPerfil) {
        this.NIF = NIF;
        this.imgPerfil = imgPerfil;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public byte[] getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(byte[] imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
    
    // Convert to JSON String
    public String toJSONString() {
        // Encode the byte[] (img) into a Base64 string to store in JSON
        String imgBase64 = (imgPerfil != null) ? Base64.getEncoder().encodeToString(imgPerfil) : "";
        return String.format(
            "{\"NIF\": %d, \"img\": \"%s\"}",
            this.NIF,
            imgBase64
        );
    }

    // Create PosterPelicula object from JSON String
    public static FotoPerfilUsuario fromJSONString(String jsonString) {
        // Simple manual parsing logic (consider using a library like Jackson/Gson for complex scenarios)
        String NIF = jsonString.replaceAll(".*\"NIF\":\\s*(\\d+).*", "$1");
        String imgBase64 = jsonString.replaceAll(".*\"img\":\\s*\"([^\"]*)\".*", "$1");

        // Decode the Base64 string back into a byte[]
        byte[] img = (imgBase64.isEmpty()) ? null : Base64.getDecoder().decode(imgBase64);

        return new FotoPerfilUsuario(NIF, img);
    }
}

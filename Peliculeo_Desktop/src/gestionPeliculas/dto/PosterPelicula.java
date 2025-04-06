/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import GUI.Utiles.Vistas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JPanel;

/**
 *
 * @author Miguelañez-PC
 */

public class PosterPelicula {
    
    private int codPelicula;    
    private String titulo;      // titulo de la película
    private byte[] img;         // img en formato BLOB (stream de bytes en java)

    public PosterPelicula() {
    }

    public PosterPelicula(int codPelicula, String titulo, byte[] img) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.img = img;
    }
    
    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
    
    /**
     * Almacenar la imágen en un directorio temporal,
     * y recuperarla como ruta absoluta del obj. File.
     * 
     * @return La ruta absoluta del File a guardar.
     */
    public String getTempImg() {
        //Convertir el poster obtenido arriba, a formato File -> sacar ruta absoluta(temp)
        File tempImagen = new File("/tmp");

        // Write the byte array to the file
        try (FileOutputStream fos = new FileOutputStream(tempImagen)) {
            fos.write(this.getImg());
        } catch (IOException ioe) {
            Vistas.mostrarErrorGUI(null, "ERROR de lectura!", "No se pudo recuperar la imágen de BD!");
        }
        return "";
    }
    
    public static byte[] compressImage(String imagePath, int maxSizeInBytes) throws IOException {
        // Cargar la imágen
        BufferedImage originalImage = ImageIO.read(new File(imagePath));

        // Redimensionar por altura y anchura, usando obj. BufferedImage
        int targetWidth = 125;
        int targetHeight = 250;
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // Convert scaled image back to BufferedImage
        BufferedImage resizedImage = new BufferedImage(
                targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

        // Compress the image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(baos)) {
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            if (param.canWriteCompressed()) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(0.7f); // Adjust quality (0.0f to 1.0f)
            }

            writer.write(null, new IIOImage(resizedImage, null, null), param);
            writer.dispose();
        }

        byte[] compressedBytes = baos.toByteArray();

        // Ensure the image fits within the MySQL BLOB size limit
        if (compressedBytes.length > maxSizeInBytes) {
            throw new IOException("Compressed image exceeds the size limit of " + maxSizeInBytes + " bytes.");
        }

        return compressedBytes;
    }
    
    public static PosterPelicula getPosterFromJSON(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, PosterPelicula.class);
        } catch (Exception err) {
            return null;
        }
    }
    
    /**
     * La clase Type ayuda a la librería Gson a 'traducir' los objetos
     * que se encuentren dentro del array JSON consumido, a la referencia
     * de tipos que use el ArrayList especificado.
     * @param jsonString
     * @return 
     */
    public static List<PosterPelicula> getArrayPostersFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoListaPelis = new TypeToken<List<PosterPelicula>>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoListaPelis);
        } catch (Exception err) {
            return null;
        }
    }
    
    // Convert to JSON String
    public String toJSONString() {
        // Encode the byte[] (img) into a Base64 string to store in JSON
        String imgBase64 = (img != null) ? Base64.getEncoder().encodeToString(img) : "";
        return String.format(
            "{\"codPelicula\": %d, \"titulo\": \"%s\", \"img\": \"%s\"}",
            this.codPelicula,
            this.titulo,
            imgBase64
        );
    }

    // Create PosterPelicula object from JSON String
    public static PosterPelicula fromJSONString(String jsonString) {
        // Simple manual parsing logic (consider using a library like Jackson/Gson for complex scenarios)
        int codPelicula = Integer.parseInt(jsonString.replaceAll(".*\"codPelicula\":\\s*(\\d+).*", "$1"));
        String titulo = jsonString.replaceAll(".*\"titulo\":\\s*\"([^\"]+)\".*", "$1");
        String imgBase64 = jsonString.replaceAll(".*\"img\":\\s*\"([^\"]*)\".*", "$1");

        // Decode the Base64 string back into a byte[]
        byte[] img = (imgBase64.isEmpty()) ? null : Base64.getDecoder().decode(imgBase64);

        return new PosterPelicula(codPelicula, titulo, img);
    }
}

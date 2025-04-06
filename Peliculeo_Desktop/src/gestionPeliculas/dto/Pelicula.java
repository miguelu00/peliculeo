package gestionPeliculas.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Miguelañez-PC
 */
public class Pelicula {
    private int id;
    private String titulo;
    private String fechaEstreno; // [dd/MM/yy HH:mm] -> USAR PARSE()
    private String genero;
    private String urlFotoPelicula;
    private int anio;

    public Pelicula() {
    }

    public Pelicula(int codPelicula, String titulo, String genero, String fechaEstreno, int anio) {
        this.id = codPelicula;
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.genero = genero;
        this.anio = anio;
    }

    public int getCodPelicula() {
        return id;
    }

    public void setCodPelicula(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public String[] toArrayString() {
        return new String[] {
            this.getTitulo(),
            this.getGenero(),
            this.getFechaEstreno(),
            String.valueOf(this.getAnio()),
            String.valueOf(this.getCodPelicula())
        };
    }
    
    //Métodos para SERIALIZAR / DESERIALIZAR un String JSON a objeto Pelicula y viceversa
    
    public String toJSONString() {
        return String.format(
            "{\"id\": %d, \"titulo\": \"%s\", \"fechaEstreno\": \"%s\", \"genero\": \"%s\", \"anio\": %d}",
            this.id,
            this.titulo,
            this.fechaEstreno,
            this.genero,
            this.anio
        );
    }
    
    public static Pelicula getPeliculaFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoPeliculas = new TypeToken<Pelicula>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoPeliculas);
        } catch (Exception err) {
            return null;
        }
    }
    
    /**
     * La clase Type ayuda a la librería Gson a 'traducir' los objetos
     * que se encuentren en el interior del array JSON consumido a la referencia
     * de tipos que use el ArrayList especificado.
     * @param jsonString
     * @return 
     */
    public static List<Pelicula> getArrayPeliculasFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoListaPelis = new TypeToken<List<Pelicula>>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoListaPelis);
        } catch (Exception err) {
            return null;
        }
    }

    @Override
    public String toString() {
        String formattedDate = fechaEstreno != null ? UtilesFecha.fechaStrToDate(fechaEstreno).toString() : "Sin-fecha";
        return "Pelicula "+ id + "{ titulo=" + titulo + "genero= " + genero + ", fechaEstreno=" + String.valueOf(formattedDate) + '}';
    }
    
    public String toStringListado() {
        return "{" + this.titulo + "; CODIGO=" + this.id + "}";
    }
}

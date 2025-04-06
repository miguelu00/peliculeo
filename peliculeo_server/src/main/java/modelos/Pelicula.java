package modelos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String titulo;
    private String fechaEstreno; // usar PARSE() [formato dd/MM/yyyy HH:mm]
    private String genero;
    private int anio;

    public Pelicula(int ID, String titulo, String fechaEstreno, String genero, int anio) {
        this.ID = ID;
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.genero = genero;
        this.anio = anio;
    }

    public Pelicula() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pelicula pelicula = (Pelicula) obj;
        return Objects.equals(ID, pelicula.getID());
    }
}

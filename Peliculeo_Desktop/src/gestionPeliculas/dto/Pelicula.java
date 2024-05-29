/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

/**
 *
 * @author Miguelañez-PC
 */
public class Pelicula {
    private int codPelicula;
    private String titulo;
    private String fechaEstreno; // [dd/MM/yy HH:mm] -> USAR PARSE()
    private String genero;
    private int anio;

    public Pelicula() {
    }

    public Pelicula(int codPelicula, String titulo, String genero, String fechaEstreno, int anio) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.genero = genero;
        this.anio = anio;
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

    @Override
    public String toString() {
        return "Pelicula "+ codPelicula + "{ titulo=" + titulo + "genero= " + genero + ", fechaEstreno=" + fechaEstreno + '}';
    }
    
    public String toStringListado() {
        return "{" + this.titulo + "; CODIGO=" + this.codPelicula + "}";
    }
}

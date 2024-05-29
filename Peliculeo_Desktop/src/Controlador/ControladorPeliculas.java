/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Exceptions.PeliculaYaExisteException;
import gestionPeliculas.dto.Pelicula;
import gestionPeliculas.dto.UtilesFecha;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Miguelañez-PC
 */
public class ControladorPeliculas {
    
    private static ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    
    public static boolean agregarPelicula(Pelicula pelicula) throws PeliculaYaExisteException {
        if (pelicula == null) {
            return false;
        }
        
        boolean hechoBD = false;
        boolean existe = existePelicula(pelicula.getTitulo(), pelicula.getAnio());
        
        
        
        if (!existe) {
            hechoBD = ControladorBBDD_mysql.hacerINSERT("peliculas", "null, '" + pelicula.getTitulo() + "', "
                    + "'" + pelicula.getGenero() + "', "
                    + "'" + pelicula.getFechaEstreno() + "', "
                    + "'" + pelicula.getAnio() + "'");
        } else {
            throw new PeliculaYaExisteException();
        }
        
        if (hechoBD) {
            //Agregar al ArrayList
            listaPeliculas.add(pelicula);
        } else {
            System.err.println("ERROR Al insertar en la base de datos!");
            return false;
        }
        return true;
    }
    
    public static Pelicula editarPelicula(Pelicula pelicula) {
        actualizarListaDesdeBBDD();
        Pelicula aux = null;
        for (Pelicula p: listaPeliculas) {
            if (p.getCodPelicula() == pelicula.getCodPelicula()) {
                aux = p;
            }
        }
        ControladorBBDD_mysql.hacerUPDATE("peliculas", new String[]{"titulo", pelicula.getTitulo(), "fechaEstreno", pelicula.getFechaEstreno(),
            "genero", pelicula.getGenero(), "anio", String.valueOf(pelicula.getAnio())}, new String[]{"codPelicula", String.valueOf(pelicula.getCodPelicula())});
        
        actualizarListaDesdeBBDD();
        return pelicula;
    }
    
    public static Pelicula getPeliculaByCodPelicula(int codPelicula) {
        Pelicula peli = null;
        
        try {
            ResultSet rs = ControladorBBDD_mysql.hacerSELECTWHERE("peliculas", "*", new String[]{"codPelicula", String.valueOf(codPelicula)});
            while (rs.next()) {
                peli = new Pelicula();
                peli.setCodPelicula(rs.getInt("codPelicula"));
                peli.setGenero(rs.getString("genero"));
                peli.setTitulo(rs.getString("titulo"));
                peli.setFechaEstreno(rs.getString("fechaEstreno"));
                peli.setAnio(rs.getInt("anio"));
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL! -> " + sqlErr.getMessage());
        }
        
        return peli;
    }
    
    public static Pelicula getPeliculaByTitulo(String titulo) {
        actualizarListaDesdeBBDD();
        for (Pelicula p: listaPeliculas) {
            if (p.getTitulo().equals(titulo)) {
                return p;
            }
        }
        return null;
    }
    
    public static Pelicula eliminarPeliculaPorCOD(int codPelicula) {
        Pelicula encontrada = null;
        for (Pelicula p: listaPeliculas) {
            if (p.getCodPelicula() == codPelicula) {
                encontrada = p;
            }
        }
        boolean hecho = ControladorBBDD_mysql.hacerDELETE("peliculas", "codPelicula", String.valueOf(codPelicula));
        if (hecho) {
            actualizarListaDesdeBBDD();
            listaPeliculas.remove(encontrada);
            return encontrada;
        }
        return null;
    }
    
    public static ArrayList<Pelicula> actualizarListaDesdeBBDD() {
        listaPeliculas = new ArrayList<Pelicula>();
        ArrayList<Integer> listaEstrenadas = new ArrayList<>();
        Pelicula pAux = null;
        
        try {
            ResultSet res = ControladorBBDD_mysql.hacerSELECT("peliculas", "*");
        
            while (res.next()) {
                pAux = new Pelicula(
                    res.getInt("codPelicula"),
                        res.getString("titulo"),
                        res.getString("genero"),
                        res.getString("fechaEstreno"),
                        res.getInt("anio")
                );
                if (!yaSeEstreno(pAux)) {
                    listaPeliculas.add(pAux);
                } else {
                    //si ya se ha estrenado, AGREGAR A LA LISTA DE ESTRENADAS.
                    listaEstrenadas.add(res.getInt("codPelicula"));
                }
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL! -> " + sqlErr.getMessage());
        }
        
        //Eliminar de la base de datos las peliculas YA ESTRENADAS, si hay...
        if (!listaEstrenadas.isEmpty()) {
            for (int i: listaEstrenadas) {
                ControladorBBDD_mysql.hacerDELETE("peliculas", "codPelicula", 
                        String.valueOf(i));
            }
        }
        return listaPeliculas;
    }
    
    /**
     * Evaluará si la lista de Peliculas de este controlador contiene alguna película con el NOMBRE y AÑO (ANIO) pasados.
     * Realiza la comprobación directamente en la BASE DE DATOS.
     * @param nombrePelicula El nombre de la pelicula a encontrar, en STRING.
     * @param anio El año anio de la película a encontrar, dato ENTERO.
     * @return TRUE  si existe una entrada con el nombre Y el año especificados, FALSE si no.
     */
    public static boolean existePelicula(String nombrePelicula, int anio) {
        ResultSet res = ControladorBBDD_mysql.hacerSELECT("peliculas", "*");
        
        try {
            while (res.next()) {
                if (Objects.equals(res.getString("titulo"), nombrePelicula) && Objects.equals(res.getInt("anio"), anio)) {
                    return true;
                }
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL => " + sqlErr.getMessage());
        }
        
        
        return false;
    }
    
    /**
     * Comparará la fecha de hoy con la fecha de estreno de la película que se indique.
     * <br>
     * Para ello, se usa un método de java.util.Date.
     * @param p La película cuyo estreno queremos comprobar
     * @return TRUE si la fecha de Hoy se pasa de la fecha de estreno de la película indicada; FALSE si aún no.
     */
    public static boolean yaSeEstreno(Pelicula p) {
        Date fechaHoy = new Date();
        return fechaHoy.after(
                UtilesFecha.fechaStrToDate(
                        p.getFechaEstreno()
                )
        );
    }

    public static ArrayList<Pelicula> getListaPeliculas() {
        actualizarListaDesdeBBDD();
        return listaPeliculas;
    }
    
}

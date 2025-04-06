package Controlador;

import Exceptions.PeliculaNoExisteException;
import Exceptions.PeliculaYaExisteException;
import GUI.Utiles.Vistas;
import clienteapi.PeliculasAPIUtils;
import gestionPeliculas.dto.Pelicula;
import gestionPeliculas.dto.PosterPelicula;
import gestionPeliculas.dto.UtilesFecha;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Miguelañez-PC
 */
public class ControladorPeliculas {
    
    private static List<Pelicula> listaPeliculas = new ArrayList<>();
    private static List<PosterPelicula> listaPosters = new ArrayList<>();
    
    public static boolean agregarPelicula(Pelicula pelicula) throws PeliculaYaExisteException {
        if (pelicula == null) {
            return false;
        }
        
        boolean hechoBD = false;
        //No hace falta comprobar si existe antes la pelicula, ya que su campo ID
        //  es auto-generado por el servidor SPRING + Hibernate
        
        //Comprobar si existe una película con el mismo combo título-año
        if (!existePelicula(pelicula.getTitulo(), pelicula.getAnio())) {
            hechoBD = !PeliculasAPIUtils.sendPostRequest(
                            "",
                            pelicula.toJSONString()
                    ).toUpperCase().startsWith("FALL");
        } else {
            throw new PeliculaYaExisteException();
        }
        
        if (hechoBD) {
            //Agregar al ArrayList local
            listaPeliculas.add(pelicula);
        } else {
            Vistas.mostrarErrorGUI(new JPanel().getRootPane(), "ERROR inserción de película", "ERROR Al insertar en la base de datos!");
            return false;
        }
        return true;
    }
    
    public static Pelicula editarPelicula(Pelicula pelicula, PosterPelicula poster) throws PeliculaNoExisteException {
        actualizarListaDesdeBBDD();
        Pelicula aux = null;
        
        boolean hechoBD;
        boolean existe = !PeliculasAPIUtils.sendGetRequest(
                    String.valueOf(pelicula.getCodPelicula())
                ).toUpperCase().startsWith("FALL");
        
        //Lo mismo en caso de que no se pudiera agregar la película
        if (existe) {
            String resultado = PeliculasAPIUtils.sendPutRequest(
                            String.valueOf(pelicula.getCodPelicula()),
                            pelicula.toJSONString()
                    );
            if (!CFG_APP.EN_PRODUCCION) {
                System.out.println(resultado);
            }
            hechoBD = !resultado.toUpperCase().startsWith("FALL");
            if (!hechoBD) {
                return null;
            }
            //Actualizar también el poster perteneciente al codPelicula
            resultado = PeliculasAPIUtils.sendPostRequest(
                            "/posters/" + String.valueOf(pelicula.getCodPelicula()),
                            poster.toJSONString()
                    );
        } else {
            throw new PeliculaNoExisteException();
        }
        
        actualizarListaDesdeBBDD();
        return pelicula;
    }
    
    public static Pelicula getPeliculaByCodPelicula(int codPelicula) {
        Pelicula peli = null;
        
        String resultado = PeliculasAPIUtils.sendGetRequest(
                    String.valueOf(codPelicula)
                );
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return null;
        }
        
        return Pelicula.getPeliculaFromJSON(resultado);
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
    
    public static PosterPelicula getPosterByCodPelicula(int codPelicula) {
        actualizarPostersDesdeBBDD();
        
        for (PosterPelicula p: listaPosters) {
            if (p.getCodPelicula() == codPelicula) { return p; }
        }
        return null;
    }
    
    public static Pelicula eliminarPeliculaPorCOD(int codPelicula) {
        Pelicula encontrada = null;
        
        String resultado = PeliculasAPIUtils.sendGetRequest(
                String.valueOf(codPelicula)
        );
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        if (resultado.toUpperCase().startsWith("FALL")) {
            return null;
        }
        
        //Si existe, enviar petición para eliminar la película
        String eliminar = PeliculasAPIUtils.sendDeleteRequest(
                String.valueOf(codPelicula)
        );
        if (eliminar.toUpperCase().startsWith("FALL")) {
            return null;
        }
        
        actualizarListaDesdeBBDD();
        
        return new Pelicula();
    }
    
    public static List<PosterPelicula> actualizarPostersDesdeBBDD() {
        String resultado = PeliculasAPIUtils.sendGetRequest("posters/");
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        //Si falla la llamada a la API, retornar NULL en la lista de Pelicula's
        if (resultado.toUpperCase().endsWith("FALL")) {
            return new ArrayList<PosterPelicula>();
        }
        //Sino, se recuperarán las películas del obj. List recogido desde la API
        listaPosters = PosterPelicula.getArrayPostersFromJSON(resultado);
        
        
        return listaPosters;
    }
    
    /**
     * Actualizar el listado local (ArrayList) de Pelicula, para reflejar el estado de la bbdd en esta capa de aplicación
     * @return 
     */
    public static List<Pelicula> actualizarListaDesdeBBDD() {
        String resultado = PeliculasAPIUtils.sendGetRequest("");
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        //Si falla la llamada a la API, retornar NULL en la lista de Pelicula's
        if (resultado.toUpperCase().endsWith("FALL")) {
            return new ArrayList<Pelicula>();
        }
        //Sino, se recuperarán las películas del obj. List recogido desde la API
        listaPeliculas = Pelicula.getArrayPeliculasFromJSON(resultado);
        
        
        return listaPeliculas;
    }
    
    /**
     * Evaluará si la lista de Peliculas de este controlador contiene alguna película con el NOMBRE y AÑO (ANIO) pasados.
     * Realiza la comprobación directamente en la API.
     * @param nombrePelicula El nombre de la pelicula a encontrar, en STRING.
     * @param anio El año anio de la película a encontrar, dato ENTERO.
     * @return TRUE  si existe una entrada con el nombre Y el año especificados, FALSE si no.
     */
    public static boolean existePelicula(String nombrePelicula, int anio) {
        ArrayList<Pelicula> encontradas = new ArrayList<Pelicula>();
        boolean hecho = false;
        String resultado = PeliculasAPIUtils.sendGetRequest("buscar/" + nombrePelicula);
        if (!CFG_APP.EN_PRODUCCION) {
                System.out.println(resultado);
        }
        
        if (!resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        //Si se ha logrado encontrar la película, o la petición ha pasado, comprobar si existe alguna con el mismo anio pasado
        encontradas = (ArrayList<Pelicula>) Pelicula.getArrayPeliculasFromJSON(resultado);
        for (Pelicula p : encontradas) {
            if (p.getAnio() == anio && p.getTitulo().equals(nombrePelicula)) {
                hecho = true;
            }
        }
        return hecho;
    }
    
    /**
     * Recogerá la IMG. del poster para la pelicula con código indicada
     */
    public static String getPosterPelicula(int codPelicula) {
        
        String resultado = PeliculasAPIUtils.sendGetRequest("posters/" + String.valueOf(codPelicula));        
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        if (!resultado.toUpperCase().endsWith("FALL")) {
            return PosterPelicula.getPosterFromJSON(resultado).getTempImg();
        } else {
            return "";
        }
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

    public static List<Pelicula> getListaPeliculas() {
        actualizarListaDesdeBBDD();
        return listaPeliculas;
    }
}

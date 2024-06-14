package controladores;

import modelos.JSONResponse;
import modelos.Pelicula;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositorios.ServicioPelicula;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class ControladorPeliculas {

    private ServicioPelicula serv;
    ArrayList<Pelicula> peliculasList = new ArrayList<>();

    @GetMapping(value = "pruebaPelis", produces = MediaType.TEXT_HTML_VALUE)
    public String pruebaServer() {
        return "<h3>Esta prueba se ejecuta correctamente!</h3>";
    }

    @PostMapping(value = "add")
    public ResponseEntity<Pelicula> savePelicula(Pelicula pelicula) {
        return new ResponseEntity<Pelicula>(serv.save(pelicula), HttpStatus.CREATED);
    }

    @GetMapping(value = "peliculas")
    public ResponseEntity<List<Pelicula>> findAll() {
        return new ResponseEntity<List<Pelicula>>(serv.findAll(), HttpStatus.FOUND);
    }

    //Agregar pelicula (comprobar si existe)
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResponse addPelicula(@RequestBody Pelicula p) {
        if (p.getTitulo() == null || p.getFechaEstreno() == null) {
            return new JSONResponse(false, "FALLO. La película introducida no es válida!");
        }
        peliculasList.add(p);
        JSONResponse jsonResponse = new JSONResponse(true, "Pelicula introducida correctamente!");
        if (!peliculasList.contains(p)) {
            jsonResponse.setDone(false); jsonResponse.setMsg("ERROR al introducir la película!");
        }

        return jsonResponse;
    }

    //Eliminar pelicula
    @DeleteMapping(value = "pelicula/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResponse borrarPelicula(@PathVariable int id) {
        int filmPos = -1;
        for (Pelicula p: peliculasList) {
            if (p.getID() == id) { filmPos = peliculasList.indexOf(p); break; }
        }
        if (filmPos != -1) { peliculasList.remove(filmPos); }
        return (filmPos == -1) ? new JSONResponse(false, "ERROR al encontrar la película!") : new JSONResponse(true, "Pelicula eliminada.");
    }
    //Modificar pelicula

    //Recuperar película [OBJECT ya que puede devolver o bien una película; o bien un JSONResponse]
    @GetMapping(value = "pelicula/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getPeliculaByID(@PathVariable int id) {
        for (Pelicula p: peliculasList) {
            if (p.getID() == id) { return p; }
        }
        //Si no se ha encontrado, devolver un objeto JSON que avise de lo ocurrido
        return new JSONResponse(false, "NO SE HA ENCONTRADO LA PELICULA CON ID " + id);
    }

    //Recuperar listado de películas
    @GetMapping(value = "peliculas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Pelicula> getAllPeliculas() {
        return peliculasList;
    }

    @GetMapping(value = "pelicula/search/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object buscarPelicula(@PathVariable String titulo) {
        List<Pelicula> peliculasCopy = peliculasList;
        peliculasCopy = peliculasCopy.stream()
                .filter(pelicula -> pelicula.getTitulo().contains(titulo))
                .toList();
        //Si no se ha encontrado nada, devolver un arraylist vacío
        return peliculasCopy;
    }

    public ArrayList<Pelicula> getPeliculasList() {
        return peliculasList;
    }

    //Agregar auto-check para ver si se ha estrenado ya la película o no
}

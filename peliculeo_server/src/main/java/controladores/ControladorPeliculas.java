package controladores;

import modelos.Pelicula;
import modelos.PosterPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositorios.ServicioPelicula;
import repositorios.ServicioPosters;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas")
public class ControladorPeliculas {

    @Autowired
    private ServicioPelicula serv;

    @Autowired
    private ServicioPosters servPosters;

    @GetMapping(value = "pruebaPelis", produces = MediaType.TEXT_HTML_VALUE)
    public String pruebaServer() {
        return "<h3>Esta prueba se ejecuta correctamente!</h3>";
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPelicula(@RequestBody Pelicula pelicula) {
        if (pelicula.getTitulo() == null || pelicula.getFechaEstreno() == null) {
            return new ResponseEntity<String>("FALLO. La película introducida no es válida!", HttpStatus.BAD_REQUEST);
        }
        Pelicula savedPelicula = serv.save(pelicula);
        return new ResponseEntity<>("Pelicula introducida correctamente!", HttpStatus.CREATED);
    }

    /**
     * Conseguir todas las películas del listado en BBDD.
     * Este es un endpoint para peticiones GET
     * @return Una lista de Pelicula's si todo OK
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> getAll() {
        List<Pelicula> peliculas = serv.findAll();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping(value = "posters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PosterPelicula>> getAllPosters() {
        List<PosterPelicula> posters = servPosters.findAll();
        return new ResponseEntity<>(posters, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarPelicula(@PathVariable int id) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            serv.deleteById(id);
            return new ResponseEntity<>("Pelicula eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar la película!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPeliculaByID(@PathVariable int id) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            return new ResponseEntity<>(peliculaOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO LA PELICULA CON ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "poster/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPosterForPeliID(@PathVariable int id) {
        Optional<PosterPelicula> posterOpt = servPosters.findById(id);
        if (posterOpt.isPresent()) {
            return new ResponseEntity<>(posterOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO LA PELICULA CON ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePelicula(@PathVariable int id, @RequestBody Pelicula updatedPelicula) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            Pelicula pelicula = peliculaOpt.get();
            pelicula.setTitulo(updatedPelicula.getTitulo());
            pelicula.setFechaEstreno(updatedPelicula.getFechaEstreno());
            serv.save(pelicula);
            return new ResponseEntity<>("Pelicula actualizada correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar la película para modificar!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "buscar/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> buscarPelicula(@PathVariable String titulo) {
        List<Pelicula> peliculas = serv.findAll().stream()
                .filter(pelicula -> pelicula.getTitulo().contains(titulo))
                .toList();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }
}
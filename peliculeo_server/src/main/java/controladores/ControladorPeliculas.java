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

import java.util.Arrays;
import java.util.Base64;
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
        return "<h3>Este endpoint se ejecuta correctamente!</h3>";
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarPelicula(@PathVariable int id) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            serv.deleteById(id);
            return new ResponseEntity<>("Pelicula eliminada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar la película!", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPeliculaByID(@PathVariable int id) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            return new ResponseEntity<>(peliculaOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO LA PELICULA CON ID " + id, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/posters/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PosterPelicula>> getPosters() {
        List<PosterPelicula> posters = servPosters.findAll();
        return new ResponseEntity<>(posters, HttpStatus.OK);
    }

    @GetMapping(value = "/posters/{codPelicula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPosterByCodPelicula(@RequestParam int codPelicula) {
        Optional<PosterPelicula> posterPelicula = servPosters.findById(codPelicula);
        if (posterPelicula.isPresent()) {
            return new ResponseEntity<>(posterPelicula.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO LA PELICULA CON ID " + codPelicula, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePelicula(@PathVariable int id, @RequestBody Pelicula updatedPelicula) {
        Optional<Pelicula> peliculaOpt = serv.findById(id);
        if (peliculaOpt.isPresent()) {
            Pelicula pelicula = peliculaOpt.get();

            pelicula.setTitulo(updatedPelicula.getTitulo());
            pelicula.setFechaEstreno(updatedPelicula.getFechaEstreno());
            serv.save(pelicula);
            return new ResponseEntity<>("Pelicula actualizada correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar la película para modificar!", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/poster/{idPelicula}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePosterPelicula(@PathVariable int idPelicula, @RequestBody PosterPelicula peliculaConPoster) {
        Optional<PosterPelicula> peliculaOpt = servPosters.findById(idPelicula);
        if (peliculaOpt.isPresent()) {
            PosterPelicula peli = new PosterPelicula();

            peli.setCodPelicula(peliculaOpt.get().getCodPelicula());
            peli.setTitulo(peliculaOpt.get().getTitulo());
            // Decodificar la imagen desde Base64
            if (peli.getImg() != null && !Arrays.toString(peli.getImg()).isEmpty()) {
                peli.setImg(Base64.getDecoder().decode(peliculaOpt.get().getImg()));
            }
            servPosters.save(peli);
            //Chequeamos (if ternario) si el póster es correcto y si se ha actualizado correctamente
            return (peli.getImg() == null ?
                    new ResponseEntity<>("ERROR inesperado al guardar el poster!", HttpStatus.EXPECTATION_FAILED)
                    :
                    new ResponseEntity<>("Póster actualizado correctamente!", HttpStatus.OK)
            );
        }
        return new ResponseEntity<>("ERROR al encontrar el poster/película especificada!", HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "buscar/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pelicula>> buscarPelicula(@PathVariable String titulo) {
        List<Pelicula> peliculas = serv.findAll().stream()
                .filter(pelicula -> pelicula.getTitulo().contains(titulo))
                .toList();

        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }
}
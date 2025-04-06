package repositorios;

import modelos.Pelicula;
import modelos.PosterPelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioPosters extends JpaRepository<PosterPelicula, Integer> {

}

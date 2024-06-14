package repositorios;

import modelos.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioPelicula extends JpaRepository<Pelicula, Integer> {

}

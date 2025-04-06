package repositorios;

import modelos.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ServicioPelicula extends JpaRepository<Pelicula, Integer> {

}

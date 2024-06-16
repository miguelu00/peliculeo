package repositorios;

import modelos.Pelicula;
import modelos.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioTickets extends JpaRepository<Ticket, Integer> {

}

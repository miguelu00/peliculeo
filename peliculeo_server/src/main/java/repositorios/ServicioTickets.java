package repositorios;

import modelos.Pelicula;
import modelos.Ticket;

import java.util.List;

public interface ServicioTickets {

    Ticket saveTicket(Ticket ticket);

    List<Ticket> getAllTickets();
}

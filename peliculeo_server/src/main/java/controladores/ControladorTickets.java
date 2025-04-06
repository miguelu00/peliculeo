package controladores;

import modelos.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositorios.ServicioPelicula;
import repositorios.ServicioTickets;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class ControladorTickets {

    @Autowired
    private ServicioTickets serv;

    @Autowired
    private ServicioPelicula servPelis;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket) {
        if (ticket.getNIF_cliente() == null) {
            throwCustom("FALLO. El nif pasado es NULL!");
        }
        if (ticket.getCodPelicula() == 0) {
            throwCustom("FALLO. El ticket introducido no es válido!");
        }
        if (ticket.getNIF_cliente().isEmpty()) {
            throwCustom("ERROR. El NIF de cliente es nulo!");
        }
        ticket.setFecha_Peli(servPelis.findById(ticket.getCodPelicula()).get().getFechaEstreno());
        serv.save(ticket);
        return new ResponseEntity<>("Ticket introducido correctamente!", HttpStatus.OK);
    }

    private ResponseEntity throwCustom(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = serv.findAll();
        if (tickets.size() > 0) {
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }
        return new ResponseEntity<>(tickets, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarTicket(@PathVariable int id) {
        System.out.println("Intentando borrar ticket con ID: " + id);

        if (!serv.existsById(id)) {
            return new ResponseEntity<>("ERROR: No se encontró el ticket!", HttpStatus.NOT_FOUND);
        }

        serv.deleteById(id);
        return new ResponseEntity<>("Ticket eliminado.", HttpStatus.OK);
    }

    // Eliminar UN ticket de entre todos los que haya con un codPelicula y pertenezcan a un NIF concreto
    @DeleteMapping(value = "/uno", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarUnTicket(@RequestBody Ticket ticket) {
        // Buscar un ticket que coincida con codPelicula y NIF_cliente
        Optional<Ticket> ticketOpt = serv.findAll().stream()
                .filter(t -> t.getCodPelicula() == ticket.getCodPelicula() &&
                        t.getNIF_cliente().equals(ticket.getNIF_cliente()))
                .findFirst();

        if (ticketOpt.isPresent()) {
            serv.delete(ticketOpt.get());  // Eliminar el ticket encontrado
            return new ResponseEntity<>("Ticket eliminado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR: No se encontró el ticket!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketByID(@PathVariable int id) {
        Optional<Ticket> ticketOpt = serv.findById(id);
        if (ticketOpt.isPresent()) {
            return new ResponseEntity<>(ticketOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO EL TICKET CON ID " + id, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "for/{nifusuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> buscarTicket(@PathVariable String nifusuario) {
        List<Ticket> tickets = serv.findAll().stream()
                .filter(ticket -> ticket.getNIF_cliente().contains(nifusuario))
                .toList();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping(value = "ticket/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTicket(@PathVariable int id, @RequestBody Ticket updatedTicket) {
        Optional<Ticket> ticketOpt = serv.findById(id);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setCodPelicula(updatedTicket.getCodPelicula());
            ticket.setNIF_cliente(updatedTicket.getNIF_cliente());
            serv.save(ticket);
            return new ResponseEntity<>("Ticket actualizado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el ticket!", HttpStatus.NO_CONTENT);
        }
    }
}

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

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticket) {
        if (ticket.getCodPelicula() == 0 || ticket.getNIFusuario() == null) {
            return new ResponseEntity<>("FALLO. El ticket introducido no es válido!", HttpStatus.BAD_REQUEST);
        }
        Ticket savedTicket = serv.save(ticket);
        return new ResponseEntity<>("Ticket introducido correctamente!", HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = serv.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarTicket(@PathVariable int id) {
        Optional<Ticket> ticketOpt = serv.findById(id);
        if (ticketOpt.isPresent()) {
            serv.deleteById(id);
            return new ResponseEntity<>("Ticket eliminado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el ticket!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTicketByID(@PathVariable int id) {
        Optional<Ticket> ticketOpt = serv.findById(id);
        if (ticketOpt.isPresent()) {
            return new ResponseEntity<>(ticketOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO EL TICKET CON ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "for/{nifusuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> buscarTicket(@PathVariable String nifusuario) {
        List<Ticket> tickets = serv.findAll().stream()
                .filter(ticket -> ticket.getNIFusuario().contains(nifusuario))
                .toList();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping(value = "ticket/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTicket(@PathVariable int id, @RequestBody Ticket updatedTicket) {
        Optional<Ticket> ticketOpt = serv.findById(id);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setCodPelicula(updatedTicket.getCodPelicula());
            ticket.setNIFusuario(updatedTicket.getNIFusuario());
            serv.save(ticket);
            return new ResponseEntity<>("Ticket actualizado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el ticket!", HttpStatus.NOT_FOUND);
        }
    }
}

package controladores;

import modelos.JSONResponse;
import modelos.Ticket;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorTickets {

    ArrayList<Ticket> ticketList = new ArrayList<>();

    @GetMapping(value = "pruebaTickets", produces = MediaType.TEXT_HTML_VALUE)
    public String pruebaServer() {
        return "<h3>Esta prueba se ejecuta correctamente!</h3>";
    }

    //Agregar pelicula (comprobar si existe)
    @PostMapping(value = "addTicket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResponse addTicket(@RequestBody Ticket t) {
        if (!t.getID().matches("^[A-Z]{3}")) {
            return new JSONResponse(false, "FALLO. El código de ticket especificado no es válido!");
        }
        if (ticketList.contains(t)) {
            return new JSONResponse(false, "FALLO. Ya existe un ticket con ese ID!");
        }
        ticketList.add(t);
        JSONResponse jsonResponse = new JSONResponse(true, "Ticket para cliente " + t.getNIFusuario() + " sacado correctamente!");
        if (!ticketList.contains(t)) {
            jsonResponse.setDone(false); jsonResponse.setMsg("ERROR al introducir el nuevo ticket!");
        }

        return jsonResponse;
    }

    //Eliminar
    @DeleteMapping(value = "ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONResponse borrarTicket(@PathVariable String id) {
        int tickPosition = -1;
        for (Ticket t: ticketList) {
            if (t.getID().equals(id)) { tickPosition = ticketList.indexOf(t); break; }
        }
        if (tickPosition != -1) { ticketList.remove(tickPosition); }
        return (tickPosition == -1) ? new JSONResponse(false, "ERROR al encontrar ese Ticket!") : new JSONResponse(true, "Ticket eliminado.");
    }
    //Modificar ticket

    //Recuperar película [OBJECT ya que puede devolver o bien una película; o bien un JSONResponse]
    @GetMapping(value = "ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getticketByID(@PathVariable String id) {
        for (Ticket t: ticketList) {
            if (t.getID().equals(id)) { return t; }
        }
        //Si no se ha encontrado, devolver un objeto JSON que avise de lo ocurrido
        return new JSONResponse(false, "NO SE HA ENCONTRADO LA TICKET CON ID " + id);
    }

    //Recuperar listado de tickets para un NIF concreto.
    @GetMapping(value = "tickets/{nif_cliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllTickets(@PathVariable String nif_cliente) {
        ArrayList<Ticket> ticketsUser = new ArrayList<>();
        for (Ticket t: ticketList) {
            if (nif_cliente.equals(t.getNIFusuario())) {
                ticketsUser.add(t);
            }
        }
        //Si termina vacío el array de tickets, devolver error; sino devolver dicho array.
        if (ticketsUser.isEmpty()) { return new JSONResponse(false, "ERROR. No existen tickets para el NIF de usuario indicado!"); }

        return ticketsUser;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    //Los tickets se borrarán automáticamente ya que sus CodPelicula/NIFUsuario quedarán huérfanos.
}

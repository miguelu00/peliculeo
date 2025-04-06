/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import clienteapi.TicketsAPIUtils;
import gestionPeliculas.dto.Pelicula;
import gestionPeliculas.dto.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguelañez-PC
 * 
 * CLASE CONTROLADOR CON ARRAYLIST DE Objs. TICKET
 */
public class ControladorTickets {
    private static List<Ticket> listaTickets = new ArrayList<>();
    
    /**
     * Insertará un nuevo objeto Ticket, llamando al método POST de la API, y
     * agregandolo a la lista de Tickets.
     * @param ticket
     * @return 
     */
    public static Ticket nuevoTicket(Ticket ticket) {
        
        //Pedirle a la base de datos la fecha de Estreno de la película elegida...
        Pelicula aux = ControladorPeliculas.getPeliculaByCodPelicula(ticket.getCodPelicula());
        String fechaPeli = aux.getFechaEstreno();

        //Agregar tanto a la BBDD, como al List<Cliente>
        
        String resultado = TicketsAPIUtils.sendPostRequest("add", ticket.toJSONString());
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        boolean hecho = !resultado.toUpperCase().startsWith("FALL");
        
        if (!hecho) {
            return null;
        }
        
        listaTickets.add(ticket);
        return ticket;
    }
    
    /**
     * Eliminará de la API, y de la lista de tickets local, el objeto Ticket
     * pasado por parámetro.
     * @param ticket
     * @return
     * @throws Exception 
     */
    public static boolean eliminarTicket(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception();
        }
        boolean found = false;
        
        String resultado = TicketsAPIUtils.sendDeleteRequest(
                String.valueOf(ticket.getIdTicket())
        );
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        
        //Si en este punto, no se ha retornado FALSE, significa que se ha borrado de la API correctamente
        for (Ticket t: listaTickets) {
            if (Objects.equals(t.getIdTicket(), ticket.getIdTicket())) {
                found = true;
                break;
            }
        }
        
        if (found) {
            listaTickets.remove(ticket);
        }
        return found;
    }
    
    public static boolean eliminarTicketPorID(int IDTicket) throws Exception {
        if (IDTicket == 0 || IDTicket <= -1) {
            return false;
        }
        boolean found = false;
        Ticket aux = null;
        
        String resultado = TicketsAPIUtils.sendDeleteRequest(
                String.valueOf(IDTicket)
        );
        if (!CFG_APP.EN_PRODUCCION) {
            System.out.println(resultado);
        }
        
        //En caso de que falle la llamada a la API
        if (resultado.toUpperCase().startsWith("FALL")) {
            return false;
        }
        
        for (Ticket t: listaTickets) {
            if (Objects.equals(t.getIdTicket(), IDTicket)) {
                found = true;
                aux = t; ///Recoger el ticket del List...
                break;
            }
        }
        
        if (found) {
            listaTickets.remove(aux); //Se debe eliminar aquí el objeto Ticket, ya que si tratamos de hacerlo en el for de arriba, nos saltaría una ConcurrentOperationException
            
        }
        return found;
    }
    
    public static List<Ticket> actualizarListaDesdeBBDD() {        
        String resultado = TicketsAPIUtils.sendGetRequest("");
        
        System.out.println(resultado);
        
        if (resultado.toUpperCase().startsWith("FALL")) {
            return new ArrayList<Ticket>();
        }
        
        //Conseguir listado de Tickets llamando a la API
        listaTickets = Ticket.getArrayTicketsFromJSON(resultado);
        if (listaTickets == null) {
            return new ArrayList<Ticket>();
        }
        return listaTickets;
    }
    
    /**
     * Actualizará el ArrayList de Cliente's actual a partir de los datos introducidos en la tabla de Tickets
     * Así, se mantendrá bien reflejado siempre el orden en ambos listados
     * @param modeloT El DefaultTableModel (swing) proveniente de la tabla en GUI.
     */
    public static void actualizarListaDesdeTablaGUI(DefaultTableModel modeloT) {
        int numColumnas = modeloT.getColumnCount();
        int numFilas = modeloT.getRowCount();
        ArrayList[] listadoTickets = new ArrayList[numFilas];
        ArrayList<String> datosTicket = new ArrayList<>();
        
        for (int i = 0; i<numFilas; i++) {
            for (int j = 0; j<numColumnas; j++) {
                datosTicket.add(String.valueOf(modeloT.getValueAt(i, j)));
            }
            listadoTickets[i] = datosTicket;
        }
        
        listaTickets = new ArrayList<Ticket>();
        
        for (ArrayList al : listadoTickets) {
            Ticket t = new Ticket(); //Cliente vacío
            t.setArray(al); //Agregar datos a través de ArrayList sacado de la tabla Swing (GUI)
            listaTickets.add(t);
        }
    }
    
    public static int getCodPelicula(int indiceLista) {
        return listaTickets.get(indiceLista).getCodPelicula();
    }
    
    public static int getIDTicketLista(int indiceLista) {
        return listaTickets.get(indiceLista).getIdTicket();
    }
    
    public static Ticket getTICKETLista(int indiceLista) {
        return listaTickets.get(indiceLista);
    }
    
    /**
     * Conseguir un ticket del listado por ID pasado por parámetro.
     * @param NIF
     * @return Cliente|NULL
     */
    public static Ticket getTicketByID(int ID_ticket) {
        Ticket encontrado = null;
        for (Ticket t: listaTickets) {
            if (Objects.equals(t.getIdTicket(), ID_ticket)) {
                encontrado = t;
                break;
            }
        }
        
        return encontrado;
    }
    
    /**
     * Actualizará la lista de Tickets (desde la BBDD), y devolverá el List de TICKETS del controlador.
     * @return Un <b>List<Ticket></b> con el listado de TICKETS actual (actualizado cada vez desde la API)
     */
    public static List<Ticket> getListaTickets() {
        actualizarListaDesdeBBDD();
        return listaTickets;
    }
}
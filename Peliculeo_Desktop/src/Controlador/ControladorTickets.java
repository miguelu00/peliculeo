/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import gestionPeliculas.dto.Pelicula;
import gestionPeliculas.dto.Ticket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguelañez-PC
 * 
 * CLASE CONTROLADOR CON ARRAYLIST DE Objs. CLIENTE
 */
public class ControladorTickets {
    private static List<Ticket> listaTickets = new ArrayList<>();
    
    public static Ticket nuevoTicket(Ticket ticket) {
        
        //Pedirle a la base de datos la fecha de Estreno de la película elegida...
        Pelicula aux = ControladorPeliculas.getPeliculaByCodPelicula(ticket.getCodPelicula());
        String fechaPeli = aux.getFechaEstreno();

        //Agregar tanto a la BBDD, como al List<Cliente>
        boolean hecho = ControladorBBDD_mysql.hacerINSERT("tickets",
                    "null,"
                    + "'" + ticket.getNIFUsuario() + "',"
                    + "" + ticket.getCodPelicula() + ","
                    + "'" + fechaPeli + "'"
                );
        
        if (hecho) {
            listaTickets.add(ticket);
        } else {
            return null;
        }
        
        return ticket;
    }
    
    public static boolean eliminarTicket(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception();
        }
        boolean found = false;
        
        for (Ticket t: listaTickets) {
            if (Objects.equals(t.getIdTicket(), ticket.getIdTicket())) {
                found = true;
                break;
            }
        }
        
        if (found) {
            ControladorBBDD_mysql.hacerDELETEID("tickets", "ID_ticket", ticket.getIdTicket());
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
        
        for (Ticket t: listaTickets) {
            if (Objects.equals(t.getIdTicket(), IDTicket)) {
                found = true;
                aux = t; ///Recoger el ticket del List...
                break;
            }
        }
        
        if (found) {
            ControladorBBDD_mysql.hacerDELETEID("tickets", "ID_ticket", IDTicket);
            listaTickets.remove(aux); //...para eliminarlo junto al ticket en BBDD; si no hacemos esto así, nos aparecerá un ConcurrentModificationException
        }
        return found;
    }
    
    public static List<Ticket> actualizarListaDesdeBBDD() {
        listaTickets = new ArrayList<Ticket>();
        Ticket tAux = null;
        
        try {
            ResultSet res = ControladorBBDD_mysql.hacerSELECT("tickets", "*");
        
            while (res.next()) {
                tAux = new Ticket(
                        res.getInt("ID_ticket"),
                        res.getInt("codPelicula"),
                        res.getString("NIF_cliente")
                );
                listaTickets.add(tAux);
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL! -> " + sqlErr.getMessage());
        }
        
        
        return listaTickets;
    }
    
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
    
    /**
     * Evaluará si la lista de Peliculas de este controlador contiene alguna película con el NOMBRE y AÑO (ANIO) pasados.
     * Realiza la comprobación directamente en la BASE DE DATOS.
     * @param NIFusuario El DNI/NIF del Usuario a encontrar, en STRING.
     * @return TRUE si existe una entrada con el NIF especificado, FALSE si no.
     */
    public static boolean existeCliente(String NIFusuario) {
        ResultSet res = ControladorBBDD_mysql.hacerSELECT("clientes", "*");
        
        try {
            while (res.next()) {
                if (Objects.equals(res.getString("NIF"), NIFusuario)) {
                    return true;
                }
            }
        } catch (SQLException sqlErr) {
            System.err.println("ERROR SQL => " + sqlErr.getMessage());
        }
        
        
        return false;
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
     * 
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
     * @return Un <b>List<Ticket></b> con el listado de TICKETS actual (actualizado cada vez desde la BBDD)
     */
    public static List<Ticket> getListaTickets() {
        actualizarListaDesdeBBDD();
        return listaTickets;
    }
}

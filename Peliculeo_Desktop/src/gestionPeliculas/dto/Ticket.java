/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import Controlador.ControladorClientes;
import Controlador.ControladorPeliculas;
import java.util.ArrayList;

/**
 *
 * @author Miguelañez-PC
 */
public class Ticket {
    private int idTicket;
    private int codPelicula;
    private String NIFUsuario;
    //private String fechaPelicula; //Se obtiene a través del codPelicula -> Consulta a BBDD

    public Ticket() {
    }

    public Ticket(int idTicket, int codPelicula, String NIFUsuario) {
        this.idTicket = idTicket;
        this.codPelicula = codPelicula;
        this.NIFUsuario = NIFUsuario;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getNIFUsuario() {
        return NIFUsuario;
    }

    public void setNIFUsuario(String NIFUsuario) {
        this.NIFUsuario = NIFUsuario;
    }
    
    public void setArray(ArrayList<String> datosComoLista) {
        this.idTicket = Integer.parseInt(datosComoLista.get(0));
        this.codPelicula = Integer.parseInt(datosComoLista.get(1));
        this.NIFUsuario = datosComoLista.get(2);
    }
    
    public String[] toArrayString() {
        Pelicula pelic = ControladorPeliculas.getPeliculaByCodPelicula(this.getCodPelicula());
        Cliente cli = ControladorClientes.getClienteByNIF(NIFUsuario);
        return new String[] {
            String.valueOf(this.getIdTicket()),
            pelic.getTitulo(),
            pelic.getFechaEstreno(),
            cli.getNombre() + " - " + cli.getDNI()
        };
    }
}

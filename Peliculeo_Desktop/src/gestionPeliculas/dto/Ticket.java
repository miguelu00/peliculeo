/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionPeliculas.dto;

import Controlador.ControladorClientes;
import Controlador.ControladorPeliculas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguelañez-PC
 */
public class Ticket {
    
    @SerializedName("ID")
    private int ID;
    
    @SerializedName("codPelicula")
    private int codPelicula;
    
    @SerializedName("NIF_cliente")
    private String nifCliente;
    
    @SerializedName("fecha_Peli")
    private String fechaPelicula; //Se obtiene a través del codPelicula -> Consulta a BBDD

    public Ticket() {
    }

    public Ticket(int idTicket, int codPelicula, String NIFUsuario) {
        this.ID = idTicket;
        this.codPelicula = codPelicula;
        this.nifCliente = NIFUsuario;
    }

    public int getIdTicket() {
        return ID;
    }

    public void setIdTicket(int idTicket) {
        this.ID = idTicket;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getNifCliente() { return nifCliente; }
    
    public void setNifCliente(String nifCliente) { this.nifCliente = nifCliente; }


    public String getFechaPelicula() {
        return fechaPelicula;
    }

    public void setFechaPelicula(String fechaPelicula) {
        this.fechaPelicula = fechaPelicula;
    }
    
    
    
    public void setArray(ArrayList<String> datosComoLista) {
        this.ID = Integer.parseInt(datosComoLista.get(0));
        this.codPelicula = Integer.parseInt(datosComoLista.get(1));
        this.nifCliente = datosComoLista.get(2);
    }
    
    public String[] toArrayString() {
        Pelicula pelic = ControladorPeliculas.getPeliculaByCodPelicula(this.getCodPelicula());
        Cliente cli = ControladorClientes.getClienteByNIF(nifCliente);
        if (cli == null) {
            cli = new Cliente();
        }
        return new String[] {
            String.valueOf(this.getIdTicket()),
            pelic.getTitulo(),
            pelic.getFechaEstreno(),
            cli.getNombre() + " - " + cli.getDNI()
        };
    }
    
    //Métodos para SERIALIZAR / DESERIALIZAR un String JSON a objeto Ticket y viceversa
    
    public String toJSONString() {
        return String.format("{\"ID\": \"%d\", \"NIF_cliente\": \"%s\", \"fecha_Peli\": \"%s\", \"codPelicula\": \"%d\"}",
            this.ID,
            this.nifCliente, 
            this.fechaPelicula,
            this.codPelicula
        );
    }
    
    public static Ticket getTicketFromJSON(String jsonString) {
        Gson elGson = new GsonBuilder()
                .setDateFormat("dd/M/yy HH:mm")
                .create();
        
        Type tipadoTickets = new TypeToken<Ticket>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoTickets);
        } catch (Exception err) {
            System.err.println("Error parsing JSON: " + err.getMessage());
            return null;
        }
    }
    
    /**
     * La clase Type ayuda a la librería Gson a 'traducir' los objetos
     * que se encuentren en el interior del array JSON consumido a la referencia
     * de tipos que use el ArrayList especificado.
     * @param jsonString
     * @return 
     */
    public static List<Ticket> getArrayTicketsFromJSON(String jsonString) {
        Gson elGson = new Gson();
        Type tipadoListaTickets = new TypeToken<List<Ticket>>(){}.getType();
        
        try {
            return elGson.fromJson(jsonString, tipadoListaTickets);
        } catch (Exception err) {
            return null;
        }
    }
}

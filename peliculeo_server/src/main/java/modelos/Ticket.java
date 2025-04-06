package modelos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int ID;
    @JsonProperty("NIF_cliente")
    private String NIF_cliente;
    @JsonProperty("codPelicula")
    private int codPelicula;
    @JsonProperty("fecha_Peli")
    private String fecha_Peli;


    /**
     * la fecha del estreno se pedirá a base de datos al hacer la consulta para mostrarse en la GUI
     */

    public Ticket() {

    }

    public Ticket(int ID, int codPelicula, String NIFusuario) {
        this.ID = ID;
        this.codPelicula = codPelicula;
        this.NIF_cliente = NIFusuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ID, ticket.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}

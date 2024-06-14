package modelos;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private int codPelicula;
    private String NIFusuario;

    /**
     * la fecha del estreno se pedirá a base de datos al hacer la consulta para mostrarse en la GUI
     */

    public Ticket() {

    }

    public Ticket(String ID, int codPelicula, String NIFusuario) {
        this.ID = ID;
        this.codPelicula = codPelicula;
        this.NIFusuario = NIFusuario;
    }

    public String getID() {
        return ID;
    }

    public void setID(String IDticket) {
        this.ID = IDticket;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getNIFusuario() {
        return NIFusuario;
    }

    public void setNIFusuario(String NIFusuario) {
        this.NIFusuario = NIFusuario;
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

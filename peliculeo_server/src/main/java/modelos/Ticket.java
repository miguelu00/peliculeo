package modelos;

import java.util.Objects;

public class Ticket {

    private String IDticket;
    private int codPelicula;
    private String NIFusuario;

    /**
     * la fecha del estreno se pedirá a base de datos al hacer la consulta para mostrarse en la GUI
     */

    public Ticket() {

    }

    public Ticket(String IDticket, int codPelicula, String NIFusuario) {
        this.IDticket = IDticket;
        this.codPelicula = codPelicula;
        this.NIFusuario = NIFusuario;
    }

    public String getIDticket() {
        return IDticket;
    }

    public void setIDticket(String IDticket) {
        this.IDticket = IDticket;
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
        return Objects.equals(IDticket, ticket.IDticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDticket);
    }
}

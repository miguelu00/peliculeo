package modelos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase necesaria, ya que la tabla PosterPelicula en la base de datos no tiene
 * PRIMARY KEY, por lo que hibernate no sabe como identificar los objetos
 * PosterPelicula
 * Esta clase solucionará este problema.
 */
public class PosterPeliculaID implements Serializable {

    private int peliculaId;

    public PosterPeliculaID() {
    }

    public PosterPeliculaID(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PosterPeliculaID that = (PosterPeliculaID) o;
        return Objects.equals(peliculaId, that.peliculaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peliculaId);
    }
}

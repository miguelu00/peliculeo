package modelos;
import jakarta.persistence.*;

@Entity
@Table(name = "posterpeliculas")
public class PosterPelicula {

    @EmbeddedId
    private PosterPeliculaID ID;

    @MapsId("peliculaId")
    @OneToOne
    @JoinColumn(name = "ID", nullable = false)
    private Pelicula pelicula;

    private String urlPosterPeli;

    public PosterPelicula() {
    }

    public PosterPelicula(Pelicula pelicula, String urlPosterPeli) {
        this.pelicula = pelicula;
        this.urlPosterPeli = urlPosterPeli;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getUrlPosterPeli() {
        return urlPosterPeli;
    }

    public void setUrlPosterPeli(String urlPosterPeli) {
        this.urlPosterPeli = urlPosterPeli;
    }
}

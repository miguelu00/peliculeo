package modelos;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "posterPeliculas")
public class PosterPelicula {


    @Id
    private int codPelicula;
    private String titulo;
    @Lob
    @Column(name = "img", columnDefinition = "blob")
    private byte[] img;

    public PosterPelicula() {
    }

    public PosterPelicula(int codPelicula, String titulo, byte[] posterPeli) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.img = posterPeli;
    }
}

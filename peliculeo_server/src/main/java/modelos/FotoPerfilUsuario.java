package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "fotoperfils")
public class FotoPerfilUsuario {

    @Id
    private String NIF;
    @Column(name = "img", columnDefinition = "blob")
    private byte[] blobFotoPerfil;

    public FotoPerfilUsuario() {}
}

package modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Usuario {

    @Id
    private String NIF;
    private String nombre;
    private String apellidos;
    private String password;
    private String fechAlta;
    private String provincia;
}

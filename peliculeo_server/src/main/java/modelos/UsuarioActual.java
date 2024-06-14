package modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String DNI;
    private String nombre;
    private String apellidos;
    private String fechAlta;
    private String provincia;
}

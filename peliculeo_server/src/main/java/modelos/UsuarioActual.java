package modelos;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class UsuarioActual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(name = "NIF")
    private String DNI;
    private String nombre;
    private String apellidos;
    @Column(name = "fecha_Alta")
    private String fechAlta;
    private String provincia;
}

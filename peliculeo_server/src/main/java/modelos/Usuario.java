package modelos;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

import java.util.Date;

@Data
@Entity
@Table(name = "clientes")
public class Usuario {

    @Id
    private String NIF;
    private String nombre;
    private String apellidos;
    private Date fecha_Alta;
    private String provincia;
    private String password;
}
package repositorios;

import modelos.UsuarioActual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<UsuarioActual, Long> {
    UsuarioActual findByEmail(String email);
    UsuarioActual login(String email, String passwd);
    UsuarioActual registro(String email, String fechNacimiento, String passwd);
}

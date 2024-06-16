package repositorios;

import modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, String> {

}

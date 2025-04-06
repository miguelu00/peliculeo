package repositorios;

import modelos.FotoPerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioFotoPerfils extends JpaRepository<FotoPerfilUsuario, String> {

}

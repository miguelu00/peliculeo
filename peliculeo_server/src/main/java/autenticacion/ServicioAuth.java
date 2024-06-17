package autenticacion;

import modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositorios.RepositorioUsuarios;

@Service
public class ServicioAuth {

    @Autowired
    private RepositorioUsuarios usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Comprobará si existe el usuario con el NIF introducido, y después si la contraseña
     * coincide con la versión descodificada almacenada en el servidor.
     * @param nif
     * @param plainPW
     * @return
     */
    public boolean authenticate(String nif, String plainPW) {
        Usuario usuario = usuarioRepository.findById(nif).orElse(null);
        if (usuario != null) {
            return passwordEncoder.matches(plainPW, usuario.getPassword());
        }
        return false;
    }
}

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

    public boolean authenticate(String nif, String plainPW) {
        Usuario usuario = usuarioRepository.findById(nif).orElse(null);
        if (usuario != null) {
            return passwordEncoder.matches(plainPW, usuario.getPassword());
        }
        return false;
    }
}

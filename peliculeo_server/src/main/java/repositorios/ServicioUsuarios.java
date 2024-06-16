package repositorios;

import modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuarios {

    @Autowired
    private RepositorioUsuarios usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ServicioUsuarios(BCryptPasswordEncoder pwEncoder) {
        this.passwordEncoder = pwEncoder;
    }

    public Usuario saveUsuario(Usuario usuario) {
        // Aquí podrías hashear la contraseña si tuviera
        // usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(String nif, Usuario usuario) {
        if (usuarioRepository.existsById(nif)) {
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    //orElse() devolverá otro valor que especifiquemos, en caso de no encontrar por ID el Usuario
    public Usuario getUsuario(String nif) {
        return usuarioRepository.findById(nif).orElse(null);
    }

    public void deleteUsuario(String nif) {
        usuarioRepository.deleteById(nif);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}

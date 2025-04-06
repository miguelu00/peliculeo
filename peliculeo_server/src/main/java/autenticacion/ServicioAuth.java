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

    /**
     * Register a new user.
     * @param newUsuario The new user's details.
     * @return A message indicating success or failure.
     */
    public String register(Usuario newUsuario) {
        // Check para ver si ya existe el usuario
        if (usuarioRepository.existsById(newUsuario.getNIF())) {
            return "Error: Ya existe un usuario con ese NIF.";
        }

        // Encriptamos el password antes de almacenar el Usuario
        newUsuario.setPassword(passwordEncoder.encode(newUsuario.getPassword()));

        // Save the new user
        usuarioRepository.save(newUsuario);

        return "Registro exitoso!";
    }
}

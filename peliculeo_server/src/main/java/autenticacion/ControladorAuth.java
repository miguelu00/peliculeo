package autenticacion;

import modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class ControladorAuth {

    @Autowired
    ServicioAuth servicio;

    /**
     * Recibirá usuario y contraseña del usuario. Por esto es una petición POST, y no de otro tipo
     * @param usuarioLogueandose El obj. Usuario que intenta el login por API
     * @return Código HTTP 200 OK -> Login exitoso
     *         Código HTTP 401 KO -> No autorizado / login fallido
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuarioLogueandose) {
        boolean isAuthenticated = servicio.authenticate(usuarioLogueandose.getNIF(), usuarioLogueandose.getPassword());
        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales incorrectas!", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody Usuario usuarioRegistrandose) {
        return new ResponseEntity<>(servicio.register(usuarioRegistrandose), HttpStatus.OK);
    }
}

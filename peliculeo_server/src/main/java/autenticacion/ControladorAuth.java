package autenticacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class ControladorAuth {

    @Autowired
    ServicioAuth servicio;

    /**
     * Recibirá usuario y contraseña del usuario. Por esto es una petición POST, y no de otro tipo
     * @param nif
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String nif, @RequestParam String password) {
        boolean isAuthenticated = servicio.authenticate(nif, password);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales incorrectas!", HttpStatus.UNAUTHORIZED);
        }
    }
}

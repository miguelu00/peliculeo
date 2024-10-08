package controladores;

import modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositorios.ServicioUsuarios;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios usuarioService;

    @PostMapping(value = "add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        if (usuarioService.getUsuario(savedUsuario.getNIF() )== null) {
            usuarioService.saveUsuario(savedUsuario);
            return new ResponseEntity<>("Usuario introducido correctamente!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("ERROR al insertar al Usuario!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios.size() > 0) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "{nif}", produces = "application/json")
    public ResponseEntity<Object> getUsuario(@PathVariable String nif) {
        Usuario usuario = usuarioService.getUsuario(nif);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NO SE HA ENCONTRADO EL USUARIO CON NIF " + nif, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{nif}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUsuario(@PathVariable String nif, @RequestBody Usuario updatedUsuario) {
        updatedUsuario.setNIF(nif);
        Usuario usuario = usuarioService.updateUsuario(nif, updatedUsuario);
        if (usuario != null) {
            return new ResponseEntity<>("Usuario actualizado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el usuario!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{nif}", produces = "application/json")
    public ResponseEntity<String> deleteUsuario(@PathVariable String nif) {
        usuarioService.deleteUsuario(nif);
        if (usuarioService.getUsuario(nif) == null) {
            return new ResponseEntity<>("Usuario eliminado.", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

package controladores;

import modelos.FotoPerfilUsuario;
import modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositorios.ServicioFotoPerfil;
import repositorios.ServicioUsuarios;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios usuarioService;

    @Autowired
    private ServicioFotoPerfil fotoPerfilService;

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.getUsuario(usuario.getNIF()) == null) {
            usuarioService.saveUsuario(usuario);
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
            return new ResponseEntity<>("NO SE HA ENCONTRADO EL USUARIO CON NIF " + nif, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "{nif}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUsuario(@PathVariable String nif, @RequestBody Usuario updatedUsuario) {
        updatedUsuario.setNIF(nif);
        Usuario usuario = usuarioService.updateUsuario(nif, updatedUsuario);
        if (usuario != null) {
            return new ResponseEntity<>("Usuario actualizado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el usuario!", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/fotoPerfil/{nif}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUsuario(
            @PathVariable String nif,
            @RequestBody FotoPerfilUsuario updatedFoto,
            @RequestParam(required = false) byte[] image) {

        updatedFoto.setNIF(nif);

        // If an image is provided, set it on the Usuario object
        if (image != null && image.length > 0) {
            updatedFoto.setBlobFotoPerfil(image);
        }
        if (image == null) {
            updatedFoto = new FotoPerfilUsuario(nif, image);
        }

        FotoPerfilUsuario fotoUsuario = fotoPerfilService.saveFotoPerfil(updatedFoto);
        if (fotoUsuario != null) {
            return new ResponseEntity<>("Usuario actualizado correctamente!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR al encontrar el usuario!", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "{nif}", produces = "application/json")
    public ResponseEntity<String> deleteUsuario(@PathVariable String nif) {
        usuarioService.deleteUsuario(nif);
        if (usuarioService.getUsuario(nif) == null) {
            return new ResponseEntity<>("Usuario eliminado.", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}

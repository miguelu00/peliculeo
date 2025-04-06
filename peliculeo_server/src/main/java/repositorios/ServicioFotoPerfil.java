package repositorios;

import modelos.FotoPerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioFotoPerfil {

    @Autowired
    private RepositorioFotoPerfils fotoPerfilsRepo;

    public FotoPerfilUsuario saveFotoPerfil(FotoPerfilUsuario datosFoto) {
        return fotoPerfilsRepo.save(datosFoto);
    }
}

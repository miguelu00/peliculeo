package repositorios;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Clase / servicio SPRING que se encargará de ejecutar un PROCEDURE
 * en el servidor de base de datos, para limpiar aquellas películas
 * cuya fecha de emisión ya haya pasado.
 */
@Service
@AllArgsConstructor
public class ServicioAutoBorrado {


    private final JdbcTemplate conexionJdbc;

    /**
     * Método que ejecutará la query SQL cada 5 minutos
      */
    @Scheduled(cron = "0 */5 * * * *")
    public void executeSqlQuery() {
        String sql = "CALL DeleteOldMovies()";  // Replace with your actual SQL query
        conexionJdbc.execute(sql);
        System.out.println("Se ejecutó el procedure DeleteOldMovies() en la base de datos. Fecha: " + new java.util.Date());
    }
}

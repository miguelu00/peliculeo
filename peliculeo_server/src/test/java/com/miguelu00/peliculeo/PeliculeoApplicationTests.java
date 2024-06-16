package com.miguelu00.peliculeo;

import controladores.ControladorPeliculas;
import controladores.HibernateUtils;
import modelos.Pelicula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
class PeliculeoApplicationTests {

    ControladorPeliculas controladorPeliculas = new ControladorPeliculas();

    /**
     * Comprobará que la conexíón con la Base de Datos es correcta
     * (que el objeto SessionFactory de Hibernate es correcto)
     */
    @Test
    void testConexionBD() {
        HibernateUtils.buildSessionFactory();
        Assertions.assertNotNull(HibernateUtils.getCurrentSession());
    }

    /**
     * Comprobar si la sesión sigue abierta una vez se ha establecido una conexión
     * estable.
     */
    @Test
    void seIniciaCorrectamente() {
        Assertions.assertNotNull(HibernateUtils.getCurrentSession());
    }

    /**
     * Comprobará que se pueden recibir todas las películas usando la API
     */
    @Test
    void testGetAllPeliculas() {
        ResponseEntity<List<Pelicula>> listaPelis = controladorPeliculas.getAll();
        Assertions.assertSame(listaPelis.getStatusCode(), HttpStatus.FOUND);
    }

    /**
     * Comprobará que se puede agregar una película usando la API
     */
    @Test
    void testAgregarPelicula() {
        ResponseEntity<String> resultado = controladorPeliculas.addPelicula(new Pelicula(1, "tre", "22-05-2024", "lel", 2000));

        Assertions.assertSame(resultado.getStatusCode(), HttpStatus.CREATED);
    }

    /**
     * Comprobará que se puede modificar una película usando la API
     */
    @Test
    void testModificarPelicula() {
        Pelicula peliTest = new Pelicula(1, "dod", "23-05-2024", "lel", 2000);
        ResponseEntity<String> resultado = controladorPeliculas.updatePelicula(1, peliTest);

        Assertions.assertSame(resultado.getStatusCode(), HttpStatus.OK);

        //comprobar que ha cambiado los datos de la peli por la pasada en el test
        Assertions.assertEquals(controladorPeliculas.getPeliculaByID(1), peliTest);
    }

    /**
     * Comprobará que se puede ELIMINAR una película usando la API
     */
    @Test
    void testEliminarPelicula() {
        ResponseEntity<String> resultado = controladorPeliculas.borrarPelicula(1);

        Assertions.assertSame(resultado.getStatusCode(), HttpStatus.OK);
    }
}

package com.miguelu00.peliculeo;

import controladores.ControladorPeliculas;
import controladores.HibernateUtils;
import modelos.Pelicula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class PeliculeoApplicationTests {

    ControladorPeliculas controladorPeliculas = new ControladorPeliculas();

    @Test
    void seIniciaCorrectamente() {
        Assertions.assertNotNull(HibernateUtils.getCurrentSession());
    }

    @Test
    void testAgregarPelicula() {
        ArrayList listaPelis = controladorPeliculas.getPeliculasList();
        controladorPeliculas.addPelicula(new Pelicula(1, "tre", "22-05-2024", "lel", 2000));
        Assertions.assertNotNull(listaPelis.get(0));
    }

    @Test
    void testConexionBD() {
        HibernateUtils.buildSessionFactory();
        HibernateUtils.openSession();
        Assertions.assertNotNull(HibernateUtils.getCurrentSession());
    }

}

package com.miguelu00.peliculeo;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelos.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TESTS UNITARIOS peliculeo!
 *
 * @TestMethodOrder -- Anotación usada para ejecutar los tests en un orden especificado por
 * anotaciones.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class PeliculeoApplicationTests extends TestsUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    //La anotación @BeforeEach se encarga de ejecutar este bloque de código antes de cada
    //  método de testing que incluya @Test detrás
    @BeforeEach
    @Order(1)
    public void configuracionInicial() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(2)
    void testAddUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNIF("12345678A");
        usuario.setNombre("Pippi");
        usuario.setApellidos("Calzaslarga");
        usuario.setFechAlta("2024-06-15");
        usuario.setProvincia("Cordoba");
        usuario.setPassword("ejemplo1");

        mockMvc.perform(post("/api/usuarios/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Usuario introducido correctamente!"));
    }

    @Test
    @Order(3)
    void testGetUsuario() throws Exception {
        mockMvc.perform(get("/api/usuarios/12345678A"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pipo");
        usuario.setApellidos("Calzolargo");
        usuario.setFechAlta("2024-06-14");
        usuario.setProvincia("Jaen");
        usuario.setPassword("contrasenia1234");

        mockMvc.perform(put("/api/usuarios/12345678A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void testGetAllUsuarios() throws Exception {
        mockMvc.perform(get("/api/usuarios/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(6)
    void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/12345678A"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado."));
    }
}

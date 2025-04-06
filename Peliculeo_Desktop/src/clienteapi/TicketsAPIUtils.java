/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteapi;

/**
 *
 * @author Miguelañez-PC
 */

import Controlador.CFG_APP;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class TicketsAPIUtils implements CFG_APP{

    private static final String URL_BASE = "http://" + API_HOST + ":" + PUERTO_HOST + "/api/tickets/";

    //Construirá el objeto HttpClient de Apache, preparandolo para la conexión
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /**
     * Especificará una petición GET; la enviará al endpoint especificado por argumento
     * @param endpoint El destino de URL/endpoint en base a la URL_BASE
     * @return 
     */
    public static String sendGetRequest(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_BASE + endpoint))
                .setHeader("User-Agent", "Java HttpClient")
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, BodyHandlers.ofString());
        } catch (Exception err) {
            return "FALLÓ!: " + err.getMessage();
        }
        
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return "Falló!: HTTP error code: " + response.statusCode();
        }
    }

    /**
     * Especificará una petición POST; la enviará al endpoint especificado por argumento y los datos por JSON
     * @param endpoint El destino de URL/endpoint en base a la URL_BASE
     * @param jsonInputString El objeto JSON (string) que representa el objeto a pasar por POST
     * @return 
     */
    public static String sendPostRequest(String endpoint, String jsonInputString) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofString(jsonInputString))
                .uri(URI.create(URL_BASE + endpoint))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, BodyHandlers.ofString());
        } catch (Exception err) {
            return "FALLÓ!: " + err.getMessage();
        }
        
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return response.body();
        } else {
            return "FALLÓ!: HTTP error code: " + response.statusCode();
        }
    }

    /**
     * Especificará una petición DELETE; la enviará al endpoint especificado por argumento.
     * @param endpoint El destino de URL/endpoint en base a la URL_BASE
     * @return El cuerpo de la respuesta HTTP, o un mensaje de error si no lo consigue
     */
    public static String sendDeleteRequest(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(URL_BASE + endpoint))
                .build();
        
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, BodyHandlers.ofString());
        } catch (Exception err) {
            return "FALLÓ!: " + err.getMessage();
        }

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return "FALLÓ!: HTTP error code: " + response.statusCode();
        }
    }
}


package com.alura.literalura.external;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexApi {

    private String URL_BASE = "https://gutendex.com";

    private String obtenerDatos(String endpoint) {
        String solicitudUrl = URL_BASE + endpoint;
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(solicitudUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    public String buscarLibro(String nombreLibro) {
        System.out.println("Buscando el libro...");

        String endpoint = "/books?search=" + nombreLibro.trim().replace(" ", "+");
        return obtenerDatos(endpoint);
    }
}

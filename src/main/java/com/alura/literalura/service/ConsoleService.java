package com.alura.literalura.service;

import com.alura.literalura.external.GutendexApi;
import com.alura.literalura.model.dto.LibroDTO;
import com.alura.literalura.model.dto.ResponseDTO;
import com.alura.literalura.model.entity.Autor;
import com.alura.literalura.model.entity.Libro;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class ConsoleService {

    public void buscarLibroTitulo(Scanner scanner, LibroService libroService, AutorService autorService) {
        GutendexApi gutendexApi = new GutendexApi();
        ConvierteDatos convierteDatos = new ConvierteDatos();

        System.out.print("Digite el nombre del libro que desea buscar: ");
        String nombreLibro = scanner.next();
        String json = gutendexApi.buscarLibro(nombreLibro);
        if (json.isEmpty()) {
            System.out.println("No se halló ningún libro con ese nombre....");
            return;
        }
        ResponseDTO responseDTO = convierteDatos.obtenerDatos(json, ResponseDTO.class);

        imprimirResultadoLibro(mapearLibro(responseDTO));

        System.out.print("¿Desea guardar el libro en la base de datos? Y/N ");
        String respuesta = scanner.next().toUpperCase().trim();
        if (respuesta.equals("Y")) {
            guardarLibro(responseDTO, libroService, autorService);
        } else if (respuesta.equals("N")) {
            System.out.println("El libro no será guardado...");
        } else {
            System.out.println("¡¡¡Respuesta no válida!!!");
        }
    }

    private void guardarLibro(ResponseDTO responseDTO, LibroService libroService, AutorService autorService) {
        try {
            Libro libro = mapearLibro(responseDTO);
            Autor autor = mapearAutor(responseDTO);

            autor = autorService.guardarAutor(autor);

            libro.setAutor(autor);
            libroService.guardarLibro(libro);

            System.out.println("Libro guardado correctamente...");
        } catch(Exception e) {
            System.out.println("[WARN] Se presentó un problema al guardar la información en la base de datos: " + e.getMessage());
        }
    }

    public void listarLibrosRegistrados(LibroService libroService) {
        List<Libro> libros = libroService.buscarTodosLosLibros();

        if (validacionListaVacia(libros,"No hay libros registrados aún")) {
            return;
        }

        System.out.println("Los libros registrados son: \n");
        libros.forEach(this::imprimirResultadoLibro);
    }

    public void listarAutoresRegistrados(AutorService autorService) {
        List<Autor> autores = autorService.buscarTodosLosAutores();

        if (validacionListaVacia(autores, "No hay autores registrados aún")) {
            return;
        }

        System.out.println("Los autores registrados son: \n");
        autores.forEach(this::imprimirResultadoAutor);
    }

    public void listarAutoresVivosEnFecha(Scanner scanner, AutorService autorService) {
        try {
            System.out.print("Digite el año: ");
            int anioSeleccionado = scanner.nextInt();

            List<Autor> autores = autorService.buscarAutoresVivosEnDeterminadoAnio(anioSeleccionado);

            if (validacionListaVacia(autores, "No hay autores registrados vivos en el año seleccionado")) {
                return;
            }

            System.out.println("Los autores registrados vivos en el año " + anioSeleccionado + "son: \n");
            autores.forEach(this::imprimirResultadoAutor);
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] No se pudo interpretar el año digitado: " + e.getMessage());
            scanner.next();
        }
    }

    public void listarLibrosPorIdioma(Scanner scanner, LibroService libroService) {
        List<String> idiomasRegistrados = libroService.bucarIdiomas();

        if (validacionListaVacia(idiomasRegistrados, "No hay libros registrados aún")) {
            return;
        }

        System.out.println("Idiomas de los libros actualmente almacenados: ");
        idiomasRegistrados.forEach(System.out::println);
        System.out.print("Teniendo en cuenta la lista anterior, escriba el idioma que quiere usar para la búsqueda: ");
        String idiomaSeleccionado = scanner.next();

        List<Libro> libros = libroService.buscarLibrosPorIdioma(idiomaSeleccionado);

        if (validacionListaVacia(libros, "\n¡No hay libros registrados que conincidan con el parámetro de búsqueda!\n")) {
            return;
        }

        System.out.println("Los libros registrados con el idioma " + idiomaSeleccionado + " son: \n");
        libros.forEach(this::imprimirResultadoLibro);
    }

    public void mostrarMenu() {
        System.out.println("""
                
                ---------------------------------------------
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. Salir
                
                """);
    }

    private void imprimirResultadoLibro(Libro libro) {
        System.out.printf("""
                --------------------Libro--------------------
                Título: %s
                Autor: %s
                Idioma: %s
                ---------------------------------------------
                """,
                libro.getTitulo(),
                libro.getAutor().getNombre(),
                libro.getIdioma());
    }

    private void imprimirResultadoAutor(Autor autor) {
        System.out.printf("""
                --------------------Autor--------------------
                Nombre: %s
                Año nacimiento: %d
                Año fallecimiento: %d
                ---------------------------------------------
                """,
                autor.getNombre(),
                autor.getAnioNacimiento(),
                autor.getAnioFallecimiento());
    }

    private boolean validacionListaVacia(List lista, String mensaje) {
        if (lista.isEmpty()) {
            System.out.printf(mensaje);
            return true;
        }
        return false;
    }

    private Libro mapearLibro(ResponseDTO responseDTO) {
        Libro libro = new Libro(responseDTO.data().get(0));
        Autor autor = mapearAutor(responseDTO);
        libro.setAutor(autor);
        return libro;
    }

    private Autor mapearAutor(ResponseDTO responseDTO) {
        Autor autor = new Autor(responseDTO.data().get(0).autores().get(0));
        return autor;
    }
}

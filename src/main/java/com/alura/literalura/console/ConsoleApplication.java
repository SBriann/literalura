package com.alura.literalura.console;

import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsoleService;
import com.alura.literalura.service.LibroService;

import java.util.Scanner;

public class ConsoleApplication {

    private Scanner scanner;
    private ConsoleService consoleService;
    private LibroService libroService;
    private AutorService autorService;

    public ConsoleApplication(ConsoleService consoleService, LibroService libroService, AutorService autorService) {
        this.consoleService = consoleService;
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void start() {
        boolean finalizar = false;
        String opcion = "";
        scanner = new Scanner(System.in);

        do {
            consoleService.mostrarMenu();
            opcion = scanner.next();
            switch (opcion) {
                case "1": {
                    consoleService.buscarLibroTitulo(scanner, libroService, autorService);
                    break;
                }
                case "2": {
                    consoleService.listarLibrosRegistrados(libroService);
                    break;
                }
                case "3": {
                    consoleService.listarAutoresRegistrados(autorService);
                    break;
                }
                case "4": {
                    consoleService.listarAutoresVivosEnFecha(scanner, autorService);
                    break;
                }
                case "5": {
                    consoleService.listarLibrosPorIdioma(scanner, libroService);
                    break;
                }
                case "0": {
                    System.out.println("¡Gracias por usar el sistema Literalura!");
                    finalizar = true;
                    break;
                }
                default: {
                    System.out.println("\n¡¡¡Opción no válida!!!\n");
                }
            }
        } while (!finalizar);

        scanner.close();
    }
}

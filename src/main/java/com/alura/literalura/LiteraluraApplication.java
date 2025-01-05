package com.alura.literalura;

import com.alura.literalura.console.ConsoleApplication;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsoleService;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private ConsoleService consoleService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsoleApplication consoleApplication = new ConsoleApplication(consoleService, libroService, autorService);
		consoleApplication.start();
	}
}

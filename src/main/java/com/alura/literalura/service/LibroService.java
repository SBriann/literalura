package com.alura.literalura.service;

import com.alura.literalura.model.entity.Libro;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public void guardarLibro(Libro libro) {
        if (!libroRepository.existsByTitulo(libro.getTitulo())) {
            libroRepository.save(libro);
        }
    }

    public List<Libro> buscarTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<String> bucarIdiomas() {
        return libroRepository.bucarIdiomas();
    }

    public List<Libro> buscarLibrosPorIdioma(String idiomaSeleccionado) {
        return libroRepository.findByIdioma(idiomaSeleccionado);
    }
}

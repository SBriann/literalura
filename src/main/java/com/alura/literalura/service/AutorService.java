package com.alura.literalura.service;

import com.alura.literalura.model.entity.Autor;
import com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public Autor guardarAutor(Autor autor) {
        return autorRepository.existsByNombre(autor.getNombre())
                ? autorRepository.findByNombre(autor.getNombre())
                : autorRepository.save(autor);
    }

    public List<Autor> buscarTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosEnDeterminadoAnio(int anioSeleccionado) {
        return autorRepository.buscarAutoresVivosEnDeterminadoAnio(anioSeleccionado);
    }
}

package com.alura.literalura.repository;

import com.alura.literalura.model.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, String> {

    boolean existsByTitulo(String titulo);

    @Query("SELECT DISTINCT l.idioma FROM Libro l ORDER BY l.idioma")
    List<String> bucarIdiomas();

    List<Libro> findByIdioma(String idioma);
}

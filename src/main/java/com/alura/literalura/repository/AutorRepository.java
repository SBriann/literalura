package com.alura.literalura.repository;

import com.alura.literalura.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Boolean existsByNombre(String nombre);

    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioFallecimiento >= :anio AND :anio >= a.anioNacimiento")
    List<Autor> buscarAutoresVivosEnDeterminadoAnio(int anio);
}

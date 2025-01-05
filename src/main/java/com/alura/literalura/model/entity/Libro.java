package com.alura.literalura.model.entity;

import com.alura.literalura.model.dto.LibroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private String id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    @ManyToOne
    private Autor autor;

    public Libro() { }

    public Libro(LibroDTO libroDTO) {
        this.id = libroDTO.id();
        this.titulo = libroDTO.tiutlo();
        this.idioma = libroDTO.idiomas().get(0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}

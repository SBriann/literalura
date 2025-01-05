# Literalura

Aplicación de consola que se comunica con una API para traer información sobre **libros** y sus **autores**, dicha información puede ser almacenada en una base de datos para posteriormente ser consultada.

## Tecnologías usadas

- `Java 17`
- `Spring Boot 3`
- `PostgeSQL`

## Librerías usadas

- `Spring Data JPA`
- `PostgreSQL Driver`
- `Jackson DataBind`

## API

La API usada para esta aplicación fue [Gutendex](https://gutendex.com/).

## Funcionalidades de la aplicación

1. **🔍 Buscar libros:** 
    - Usando la API anteriormente mencionada, se buscan libros por `título`, tomando el primero de la lista que se obtiene como respuesta, la cual está conformada por información básica del libro y sus autores.
    - Se presenta la posibilidad de almacenar la información del `libro` y `autor` (se tiene en cuenta el primero de la lista) en la base de datos.
2. **📚 Listar libros almacenados:** La información de los `libros` que se presenta corresponde a la que se ha almacenado en la base de datos. 
3. **👥 Listar autores almacenados:** La información de los `autores` que se presenta corresponde a la que se ha almacenado en la base de datos.
4. **📅️ Listar autores vivos en un determinado año:** Consultando la base de datos, se muestran solo aquellos `autores` que estén vivos en el año proporcionado por el usuario.
5. **🗣️ Listar libros por idioma:** Teniendo en cuenta una lista de abrevaciones en inglés de los idiomas que han sido almacenados, el usuario selecciona uno de ellos para observar únicamente aquellos libros almacenados que correspondan a dicho `idioma`.
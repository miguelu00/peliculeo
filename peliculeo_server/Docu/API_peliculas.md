# END POINTS: Películas

## GET /api/peliculas/pruebaPelis
Devuelve una respuesta HTML simple para verificar la conectividad del servidor.

**Método HTTP**: GET  
**Devuelve**: `text/html`

---

## POST /api/peliculas/
Añade una nueva película a la base de datos.

**Método HTTP**: POST  
**Consumo**: `application/json`  
**Devuelve**: `application/json`

**Cuerpo de la solicitud**: Representación JSON de `Pelicula`.  
Si los datos de entrada son inválidos (por ejemplo, `titulo` o `fechaEstreno` faltan), devuelve un error `400 BAD_REQUEST`.

---

## GET /api/peliculas/
Recupera todas las películas de la base de datos.

**Método HTTP**: GET  
**Devuelve**: `application/json`

---

## DELETE /api/peliculas/{id}
Elimina una película por su ID.

**Método HTTP**: DELETE  
**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a eliminar.  
Si no se encuentra la película, devuelve `204 NO_CONTENT`.

---

## GET /api/peliculas/{id}
Recupera una película por su ID.

**Método HTTP**: GET  
**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a recuperar.  
Si no se encuentra la película, devuelve `204 NO_CONTENT`.

---

## PUT /api/peliculas/{id}
Actualiza una película con el ID proporcionado.

**Método HTTP**: PUT  
**Consumo**: `application/json`  
**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a actualizar.  
**Cuerpo de la solicitud**: Representación JSON actualizada de `Pelicula`.  
Si no se encuentra la película, devuelve `204 NO_CONTENT`.

---

## GET /api/peliculas/buscar/{titulo}
Busca películas por su título.

**Método HTTP**: GET  
**Devuelve**: `application/json`

**Variable de ruta**: `titulo` - Parte o la totalidad del título de la película a buscar.  
Devuelve una lista de películas cuyo título contiene el texto proporcionado.

---

## GET /api/peliculas/posters/
Recupera todos los pósters de películas disponibles.

**Método HTTP**: GET  
**Devuelve**: `application/json`

---

## POST /api/peliculas/poster/{idPelicula}
Actualiza el póster de una película.

**Método HTTP**: POST  
**Consumo**: `application/json`  
**Devuelve**: `application/json`

**Variable de ruta**: `idPelicula` - ID de la película a actualizar con un nuevo póster.  
**Cuerpo de la solicitud**: Representación JSON de `PosterPelicula`.  
Si no se encuentra el póster o la película, devuelve `204 NO_CONTENT`.

---

# Manejo de Errores

- **400 BAD_REQUEST**: Datos de entrada inválidos.
- **204 NO_CONTENT**:
    - No se encontró la película o póster con el ID especificado.
    - No se encontraron resultados para la búsqueda.
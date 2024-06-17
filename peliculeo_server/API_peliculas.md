### END POINTS: Películas

#### GET /api/peliculas/pruebaPelis
Devuelve una respuesta HTML simple para verificar la conectividad del servidor.

**Método HTTP**: GET

**Devuelve**: `text/html`

---

#### POST /api/peliculas/add
Añade una nueva película a la base de datos.

**Método HTTP**: POST

**Consumo**: `application/json`

**Devuelve**: `application/json`

**Cuerpo de la solicitud**: Representación JSON de `Pelicula`

---

#### GET /api/peliculas/
Recupera todas las películas de la base de datos.

**Método HTTP**: GET

**Devuelve**: `application/json`

---

#### DELETE /api/peliculas/{id}
Elimina una película por su ID.

**Método HTTP**: DELETE

**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a eliminar

---

#### GET /api/peliculas/{id}
Recupera una película por su ID.

**Método HTTP**: GET

**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a recuperar

---

#### PUT /api/peliculas/{id}
Actualiza una película con el ID proporcionado.

**Método HTTP**: PUT

**Consumo**: `application/json`

**Devuelve**: `application/json`

**Variable de ruta**: `id` - ID de la película a actualizar

**Cuerpo de la solicitud**: Representación JSON actualizada de `Pelicula`

---

#### GET /api/peliculas/buscar/{titulo}
Busca películas por su título.

**Método HTTP**: GET

**Produce**: `application/json`

**Variable de ruta**: `titulo` - Título de la película a buscar

---

### Manejo de Errores

- `BAD_REQUEST` (400): Formato de datos de entrada inválido.
- `NOT_FOUND` (404): Película con el ID o criterio especificado no encontrada.


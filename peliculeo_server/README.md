# PELICULEO SERVER

Gestiona fácilmente los estrenos de películas de Peliculeo,
a través de esta sencilla API Spring Boot.

Para usarla, tan solo pon en marcha la clase PeliculeoApplication.

## Documentación API REST

### END POINTS: Películas

#### GET /api/peliculas/pruebaPelis
Devuelve una respuesta HTML simple para verificar la conectividad del servidor.

**Método HTTP**: GET

**Produce**: `text/html`

---

#### POST /api/peliculas/add
Añade una nueva película a la base de datos.

**Método HTTP**: POST

**Consumo**: `application/json`

**Produce**: `application/json`

**Cuerpo de la solicitud**: Representación JSON de `Pelicula`

---

#### GET /api/peliculas/all
Recupera todas las películas de la base de datos.

**Método HTTP**: GET

**Produce**: `application/json`

---

#### DELETE /api/peliculas/{id}
Elimina una película por su ID.

**Método HTTP**: DELETE

**Produce**: `application/json`

**Variable de ruta**: `id` - ID de la película a eliminar

---

#### GET /api/peliculas/{id}
Recupera una película por su ID.

**Método HTTP**: GET

**Produce**: `application/json`

**Variable de ruta**: `id` - ID de la película a recuperar

---

#### PUT /api/peliculas/{id}
Actualiza una película con el ID proporcionado.

**Método HTTP**: PUT

**Consumo**: `application/json`

**Produce**: `application/json`

**Variable de ruta**: `id` - ID de la película a actualizar

**Cuerpo de la solicitud**: Representación JSON actualizada de `Pelicula`

---

#### GET /api/peliculas/pelicula/search/{titulo}
Busca películas por su título.

**Método HTTP**: GET

**Produce**: `application/json`

**Variable de ruta**: `titulo` - Título de la película a buscar

---

### Manejo de Errores

- `BAD_REQUEST` (400): Formato de datos de entrada inválido.
- `NOT_FOUND` (404): Película con el ID o criterio especificado no encontrada.

### END POINTS: Tickets

#### POST /api/tickets/add
Añade un nuevo ticket a la base de datos.

**Método HTTP**: POST

**Consumo**: `application/json`

**Produce**: `application/json`

**Cuerpo de la solicitud**: Representación JSON de `Ticket`

---

#### GET /api/tickets/getall
Recupera todos los tickets de la base de datos.

**Método HTTP**: GET

**Produce**: `application/json`

---

#### DELETE /api/tickets/ticket/{id}
Elimina un ticket por su ID.

**Método HTTP**: DELETE

**Produce**: `application/json`

**Variable de ruta**: `id` - ID del ticket a eliminar

---

#### GET /api/tickets/ticket/{id}
Recupera un ticket por su ID.

**Método HTTP**: GET

**Produce**: `application/json`

**Variable de ruta**: `id` - ID del ticket a recuperar

---

#### GET /api/tickets/ticket/for/{nifusuario}
Busca tickets por el NIF del usuario.

**Método HTTP**: GET

**Produce**: `application/json`

**Variable de ruta**: `nifusuario` - NIF del usuario para buscar tickets

---

#### PUT /api/tickets/ticket/{id}
Actualiza un ticket con el ID proporcionado.

**Método HTTP**: PUT

**Consumo**: `application/json`

**Produce**: `application/json`

**Variable de ruta**: `id` - ID del ticket a actualizar

**Cuerpo de la solicitud**: Representación JSON actualizada de `Ticket`

---

### Manejo de Errores

- `BAD_REQUEST` (400): Formato de datos de entrada inválido.
- `NOT_FOUND` (404): Ticket con el ID o criterio especificado no encontrado.
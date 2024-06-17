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

#### GET /api/tickets/tickets/for/{nifusuario}
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
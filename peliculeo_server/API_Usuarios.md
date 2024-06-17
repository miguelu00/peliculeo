## END POINTS: Usuarios

### POST /api/usuarios/add

Añade un nuevo usuario a la base de datos.

**Método HTTP**: POST

**Consumo**: application/json

**Devuelve**: application/json

**Cuerpo de la solicitud**: Representación JSON de Usuario

json
~~~~
{
"nif": "string",
"nombre": "string",
"apellido": "string",
"email": "string",
"telefono": "string"
}
~~~~
### GET /api/usuarios/

Recupera todos los usuarios de la base de datos.

Método HTTP: GET

Devuelve: application/json
### GET /api/usuarios/{nif}

Recupera un usuario por su NIF.

Método HTTP: GET

Devuelve: application/json

Variable de ruta: nif - NIF del usuario a recuperar

### PUT /api/usuarios/{nif}

Actualiza un usuario con el NIF proporcionado.

Método HTTP: PUT

Consumo: application/json

Devuelve: application/json

Variable de ruta: nif - NIF del usuario a actualizar

Cuerpo de la solicitud: Representación JSON actualizada de Usuario

### DELETE /api/usuarios/{nif}

Elimina un usuario por su NIF.

Método HTTP: DELETE

Devuelve: application/json

Variable de ruta: nif - NIF del usuario a eliminar

<br>

Manejo de Errores

    BAD_REQUEST (400): Formato de datos de entrada inválido.
    NOT_FOUND (404): Usuario con el NIF especificado no encontrado.
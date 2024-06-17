# Dockerfile
FROM mysql:latest

# Copiará el script de DDL a un directorio del docker
COPY ./bdpeliculas.sql /docker-entrypoint-initdb.d/

# Configuración de entorno para el servicio MySQL
ENV MYSQL_ROOT_PASSWORD=4321

# Darle permiso de lectura al directorio con el script DDL de MySQL, y permisos para la BD
RUN chmod -R 755 /docker-entrypoint-initdb.d
# Exponer el puerto para MySQL
EXPOSE 3306
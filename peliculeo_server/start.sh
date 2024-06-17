#!/bin/bash

# Comprobar si ya existe un contenedor con nombre 'mysql'
#  Si existe, parar y eliminarlo;; eliminar también todos los directorios originales de docker-entrypoint
if [ "$(docker ps -aq -f name=mysql)" ]; then
    docker stop mysql
    docker rm mysql
fi

# Comprobar si existe el volumen (var. de directorio en docker) 'mysql_data'
if [ "$(docker volume ls -q -f name=mysql_data)" ]; then
    echo "Eliminando viejo directorio 'mysql_data' en el contenedor..."
    docker volume rm mysql_data
fi

docker buildx build -t custom-mysql .

docker run -d --name mysql -p 3306:3306 -v mysql_data:/var/lib/mysql custom-mysql &

str=""
while true; do
  read -p "SERVIDOR INICIADO. Escriba 'done' para cerrarlo..." str
  if [ "$str" == "done" ]; then
    docker stop mysql
    break
  fi
  echo "SERVIDOR APAGADO! Desconectando el docker..."
done
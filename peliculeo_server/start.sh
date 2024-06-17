#!/bin/bash

# Comprobar si ya existe un contenedor con nombre 'mysql'
#  Si existe, parar y eliminarlo
if [ "$(docker ps -aq -f name=mysql)" ]; then
    docker stop mysql
    docker rm mysql
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
done
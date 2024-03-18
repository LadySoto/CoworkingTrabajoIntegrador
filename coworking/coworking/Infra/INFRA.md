## PASOS CONEXIÓN BBDD 
### Paso 1: ejecutar mysql en un contenedor de Docker
```shell 
docker run --name mysql -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=admin --restart unless-stopped mysql:latest
```
Reemplazar las X por el container id (docker ps)
```shell 
docker exec -it XXXXX mysql -uroot -p 
```

```sql
CREATE DATABASE coworking;
```
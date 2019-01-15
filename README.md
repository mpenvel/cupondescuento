# Cupón de descuento

Repositorio que contiene el código para aplicar un código de descuento a un pedido.

## Ejecución de la aplicación

Para poder ejecutar la aplicación hay que seguir una serie de pasos. Lo primero es compilar el código y generar un ejecutable de formato .jar, para compilar el código hay que ejecutar el siguiente comando:

```
mvn clean install
```

Una vez generado el ejecutable, hay que copiar el archivo cupondescuento.jar que está en la carpeta target en la carpeta docker/jar y ejecutar en esta carpeta el siguiente comando:

```
docker-compose up
```

## Swagger

Para ver los endpoints expuestos se puede acceder a [Swagger](http://localhost:8080/swagger-ui.html) 




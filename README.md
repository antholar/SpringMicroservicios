# SpringMicroservicios

Centralizacion de configuraciones remotas

## Apuntes
Para que consulte las configuraciones remotamente, se tiene q subir las configuraciones a github y se vinciula segun el URL generado desde github 
Una ves vinculado se cambia en el proyecto de configuraciones en ves del file la url
```
spring.cloud.config.server.git.uri=https://github.com/antholar/SpringConfiguraciones.git
```

Luego se verifica si las configuraciones son correctas en el url de verificacion
```
localhost:8090/api/items/obtener-config
localhost:8888/servicio-items/prod
```
# SpringMicroservicios

Centralizacion de configuraciones

## Apuntes
Centralizacion de la configuracion
Al centralizar las configuraciones en el servicio, se pueden realizar configuraciones mas rapido sin modificar codigo.

Las dependencias que debe de tener el servicio son
- Spring boot dev tools
- Spring cloud config -> Config Server

En el archivo de propiedades se ingresan los siguientes comandos, indicando x ahora la ruta donde se encuentra el archivo git de configuracion
	spring.application.name=config-server
	server.port=8888
	spring.cloud.config.server.git.uri= file:////C:/Users/antho/Documents/workspace-sts-4.0.0.RELEASE

Para verificar si el servidor funciona ejecutarlo
El servidor leera todos los archivos de propiedades pero para verificar si lee el archivo le pasamos
	localhost:8888/servicio-items/default

En el proyecto item se agrega la configuraciones
- Spring cloud config -> Config Client
Se agrega otro archivo de propiedades que se llamara bootstrap.properties en la misma ruta que el application.properties
El proyecto cliente al ejecutarse lee primero las configuraciones de bootstrap.properties

### Test de configuracion
Para verificar si se jalaron las configurciones correctamente, se creara un url donde retornara el puerto y el mensaje que se escribio en el archivo de configuraciones git
Para verificar el url es
```
localhost:8005/obtener-config
localhost:8090/api/items/obtener-config  //atraves de zuul
```

El proyecto item vincula el nombre que se puso en  el archivo boostrap con el nombre del archivo de properties de git 

### Cambio de los perfiles
Se agrego en el controlador de item la siguiente configuracion para poder diferenciar si se esta con un perfil de desarrollo, defecto o produccion
```
localhost:8888/servicio-items/prod
localhost:8888/servicio-items/dev
```

Cabe señalar que aparecen dos configuraciones, eso es porque primero lee el archivo de defecto pero le da prioridad al perfil que se definio


### Actualizacion de perfiles sin tener que detener el proyecto
El servidor de configuraciones se ejecuta como un singleton, por lo que cuando hay cambios se tiene q reiniciar todo, todas las configuraciones son las mismas para todos. Para poder refrescar las configuraciones, en el controlador de item se debe de agregar la etiqueta 
```
@RefreshScope
```

Tambien se debe de agregar la siguiente dependencia al proyecto de item, para que refrescar el proyecto depues de realizadas los cambios a las configuraciones
- Ops -> Spring boot actuator

En el bootstrap se debe de agregar la linea. Que permite actualizar todos los componentens ingresados con refreshScope, en este caso se puso * para que actualize todos los componentes
```
management.endpoints.web.exposure.include=*
```

Se hacen los cambios en los archivos de propiedades y con git se hace el commit
Para actualizar se debe de ejecutar el siguiente url en POST
```
localhost:8005/actuator/refresh				
```

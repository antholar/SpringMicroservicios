# SpringMicroservicios

Creacion del servicio de Eureka

## Apuntes

Creacion del servidor eureka que registra los microservicios que se conecten y los agrupa para facilitar la comunicacion entre el cliente y el servidor

### Configuracion del servicio Eureka

Las dependencias que se utilizaron en el servidor Eureka son 
	- Eureka Server
	- Dev Tools
Se debe de agregar la dependencia de jaxb, solo si se esta trabajando con un jdk mayor al 9
```
<dependency>
  <groupId>org.glassfish.jaxb</groupId>
  <artifactId>jaxb-runtime</artifactId>
</dependency>
```

El servidor Eureka debe de tener las siguientes configuraciones
```
spring.application.name=servicio-eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

### Configuracion de los servicios
Los otros servicios, para que se conecten al servidor Eureka, deben de tener la siguiente dependencia
- Eureka discovery

Tambien en el archivo de configuracion de los servicios es recomendable agregar la siguiente etiqueta. 
- @EnableEurekaClient

Este paso no es del todo necesario, ya que al agregar la dependencia de "Eureka Discovery" se entiende q es cliente pero siempre es bueno poner la etiqueta para indicarlo.

Luego se indica en los archivos de propiedades de cada servicio el url del servidor eureka
```
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```
Se debe de quitar las configuraciones de Ribbon ya que Eureka Cliente ya lo tiene implementado
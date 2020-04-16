# SpringMicroservicios

Implementacion de Hyxtrix

## Apuntes
- Hyxtrix: tolerancia de fallas en los microservicios

Se configuro en los servicios, la generacion de puertos aleatoreos Se agregan las siguientes lineas en el application.properties 
```
server.port=${PORT:0} 
eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}} 
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

Para habilitar histrix se debe de agregar la dependencia en el proyecto item - Hystrix El la clase de configuraciuon se agrega la siguiente etiqueta 
```
@EnableCircuitBreaker
```
Esta etiqueta sirve para ver errores de comunicacion o cortes, tiempos fuera, etc. Esta dependencia incluye Ribbon para analizar los posibles errores como latencia

En el servicio item, se agrego la etiqueta de 
```
@HystrixCommand(fallbackMethod = "metodoAlternativo") 
```
Definiendo la funcion que debe de tomar cuando hay un error. Esta funcion es creada en el controlador
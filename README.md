# SpringMicroservicios

Implementacion de servicio Zuul

## Apuntes
Configuracion Zuul para la seguridad de las peticiones. da la ventaja que solo se configura una ves la seguridad en ves de dar spring security por cada proyecto

Caracteristicas
- Erutamiento dimanico
- integracion con ribbon - Balanceo de carga al hacer las peticiones
- Tiene distintos filtros para dar seguridad a los distintos microservicios
- Monitorear metricas y tolerancia de errores 

Se crea otro proyecto con las dependencias: 
- Spring boot devtools
- Eureka discovery client
- zuul 
- spring web

La ventaja de zuul es que centraliza todas las rutas en una sola ruta, manejando errores, seguridad y peticiones en un solo proyecto. Dandole una doble seguridad a los proyectos 
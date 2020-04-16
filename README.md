# SpringMicroservicios

Actualizacion de Feign con crud y Zuul

## Apuntes

Implementacion de los metodos faltantes con Feign

Al igual que los metodos implementados en restTemplate, se implementaron los metodos con feign

Basicamente la modificacion que se realizo fue en la clase ProductoClienteRest, ya que esta es la interfaz con producto a traves de Feign

Tambien se modifico el servicio ItemServiceFeign que eran implementaciones que quedaron pendientes en el anterior bloque. Esta implementacion hace llamado a la conexion ProductoClienteRest x lo que recien se implemento en el servicio
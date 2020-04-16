# SpringMicroservicios

Se agregaron las dependencias de Feign y Ribbon en los proyectos

## Apuntes

Feign: sirver para facilitar las conexiones con otros servicio a traves del nombre del proyecto. La diferencia mas clara con RestTempate es que no se debe de ingresar la url del otro proyecto, en ves de eso se debe de crear una clase con todos los metodos del servidor, para poder ser llamados 

Ribbon: permite balancear la carga segun la disposicion de los servicios. 

El uso de Ribbon facilita las conexiones de Feign, ya no siendo necesario ingresar la IP solo el nombre del servicio
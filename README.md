# SpringMicroservicios

Centralizacion de Entitys

## Apuntes

La idea de crear un proyecto que centralize algunas propiedades de los otros proyectos, en este caso el entity, que seran utilizados en items y productos

Es necesario crear otro proyecto el que llamaremos commons.

Este proyecto tiene que tener los paquetes
- Spring Data JPA
- H2 Database

Del archivo SpringServicioCommonsApplication se debe de borrar las lineas de configuracion de un proyecto JPA, debiendo quedar de la siguiente forma
```
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringServicioCommonsApplication {
}
```
Tambien de POW.xml se quitaran el plugin de MAVEN
```
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

Se crea el package y la clase entity

Se compila ingresando a la carpeta del proyecto desde el cmd y se ingresa el siguiente codigo
```
mvnw.cmd install
```

Esto compilara el proyecto y generara un jar en la carpeta target

-------------------------------

Se debe de configurar en la clse SpringServicioCommonsApplication lo que es la autoconfiguracion de H2, como se usara en item y en productos no necesariamente va a tener configuradas las variables de H2. se agrega la siguiente etiqueta
```
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
```

Tambien  se borro del pow.xml la dependencia h2 para que no sea obligatoria de compilar, pero esto es opcional 

Se debe de copiar del pow.xml de commons los siguientes etiquetas para ser puestas como dependencias en productos
```
<groupId>com.spring.app.commons</groupId>
<artifactId>spring-servicio-commons</artifactId>
<version>0.0.1-SNAPSHOT</version>
```

Se debe reemplazar todas las dependencias que se haga a 
```
import com.spring.app.productos.models.entity.Producto;
```
que viene a ser el entity de la clase, con el entity del commons o del proyecto compilado
```
import com.spring.app.commons.models.entity.Producto;
```
tambien se puede eliminar la clase entity del proyecto 

--------------------------------------

Se debe de indicar en la clase de configuracion  que debe de leer los entity del proyecto commons. Se le pasa como etiqueta el package de donde estan guardados los entity. Si tuvieramos otros packages solo se agrega con comas los otros paquetes
```
@EntityScan({"com.spring.app.commons.models.entity"})
```

Para items se hace lo mismo q en el producto, hasta la configuracion de la clase de configuraciones, si se usara base de datos no se debe de usar la exclusion de las configuraciones del datasource
```
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
```

En item no se usa @EntityScan ya que no es necesario para item
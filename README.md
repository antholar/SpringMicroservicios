# SpringMicroservicios

Spring Cloud Security implementando OAuth2 y JWT

## Apuntes

Se va a tener que crear otro proyecto el cual valide las autorizaciones de los usuarios . 

Se creara un microservicio que gestione las autorizaciones generando los jwt. Zuul gestionara el acceso a los recursos utilizando el servidor de autorizaciones y el serviico de usuraios

----------------------------------

Proyecto de clientes
Se necesita crear un proyecto con los siguientes microservicios. 
- Spring boot devtools
- spring data jpa
- h2
- eureka discovery client
- spring web
	
Se configura el servicio para que tome un puerto aleatoreo y para que se puede conectar al servidor eureka como cliente. esto servira para facilitar las conexiones con el serviicio atraves del nombre

Las configuraciones realizadas son las siguientes
 - selecion automatica del puerto
	``` 
	server.port=${PORT:0}   
	```
 - generacion del id de manera automatica del servicio
 	```
	eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}
	```
 - es necesario especificar la ruta al servidor eureka
 	```
	eureka.client.service-url.defaultZone=http://localhost:8761/eureka
	```
 - muestra las consultas que realiza a la base de datos en SQL en la consula
 	```
	logging.level.org.hibernate.SQL=debug 
	```

---------------------------------------------

Se creara los entity de usuario, roles y usuarios_roles

Se crearon los vinculos entre los entitys de Usuarios y roles. Para eso se creo en usuarios una variable que es la siguiente 
```
@ManyToMany(fetch = FetchType.LAZY) 
private List<Role> roles;
```

Donde establece el vinculo con el entity Role. este vinculo crea una tabla intermedia, uniendo el ide del usuario y varios roles. Para ver todos los roles relacionados, solo se debe de consutar en el entity el array, la automaticamente el crud crea la tabla intermedia

Para personalizar esta tabla intermedia se debe de ingresar el siguiente codigo
```
@ManyToMany(fetch = FetchType.LAZY) 
@JoinTable(name="usuarios_to_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"),
, uniqueConstraints = {@UniqueConstraint(columnNames = {"user_idd","role_id"})} )
private List<Role> roles;
```

Donde joinColumns quiere decir el nombre de la columna principal y el inverseJoinColumns determina el id de la llave foranea en este caso sera roles


Se determina otra variable que es uniqueConstraints, que sirve para indicar que no debe de haber claves repetidas

----------------------------------------

De igual forma para el entity roles se debe de especificar el enlace
```
@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles") 
private List<Usuario> usuarios;
```

El mappedBy indica a q variable se mapeara, en este caso se mapeara a la variable Roles de la clase Usuario, realizando la relacion
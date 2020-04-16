package com.spring.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker  //sirve para controlar errores de comunicacion o cortes, tiempos fuera, etc. poniendo otro evento en item en caso de error. La comunicacion con los microservicios, incluye ribben para analizar los posibles errores como latencia
@EnableEurekaClient
@EnableFeignClients   //es una libreria que facilita el http de los clientes y su comunicacion con servicios web, en ves de hacer la configuracion de rest con feign te facilita la comunicacion a traves del nombre
@SpringBootApplication

//quitada la dependencia de ribbon porque se usa EUREKA
//@RibbonClient(name = "servicio-productos") //configuracion del ribbon para que el cliente se pueda conectar
public class SpringServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServicioItemApplication.class, args);
	}

}

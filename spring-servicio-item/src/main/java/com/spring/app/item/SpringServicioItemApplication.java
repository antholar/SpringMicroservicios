package com.spring.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients   //es una libreria que facilita el http de los clientes y su comunicacion con servicios web
@SpringBootApplication
@RibbonClient(name = "servicio-productos") //configuracion del ribbon para que el cliente se pueda conectar
public class SpringServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServicioItemApplication.class, args);
	}

}

package com.spring.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //se habilita eureka server en el proyecto
@SpringBootApplication
public class SpringServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServicioEurekaServerApplication.class, args);
	}

}

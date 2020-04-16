package com.spring.app.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	//resttempalte es un cliente para acceder a otros microservicios atraves de rest
	//cliente http para poder llamar a otros clientes que tengan otros servicios
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
		
	}
}

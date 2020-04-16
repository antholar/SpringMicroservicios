package com.spring.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.app.item.models.Producto;

//con feign se especifica el nombre del servidor al que se quiere conectar junto con la URL
//
//@FeignClient(name = "servicio-productos", url="localhost:8001") //configuraccion sin ribbon
@FeignClient(name = "servicio-productos") //con ribbon no es necesario poner la url xq se configura
public interface ProductoClienteRest {
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
}

package com.spring.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.app.commons.models.entity.Producto;

//con feign se especifica el nombre del servidor al que se quiere conectar junto con la URL
//
//@FeignClient(name = "servicio-productos", url="localhost:8001") //configuraccion sin ribbon
@FeignClient(name = "servicio-productos") //con ribbon no es necesario poner la url xq se configura
public interface ProductoClienteRest {
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto,@PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
	
	
}

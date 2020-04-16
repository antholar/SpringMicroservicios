package com.spring.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.productos.models.entity.Producto;
import com.spring.app.productos.models.service.IProductoService;

@RestController   //devuleve el contenido en un json
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	//otra forma de obtener los datos del properties
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		//return productoService.findAll(); //configuracion anterior, antes de agregar el puerto
		return productoService.findAll().stream().map(producto -> {
					//producto.setPort( Integer.parseInt(env.getProperty("local.server.port") ) );
					producto.setPort( port );
					return producto;
				}).collect(Collectors.toList() );
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port") ) );
		producto.setPort( port );
		
		//forzar un error para verificar hystrix
		boolean ok =false;
		if(ok == false) {
			throw new Exception ("No se puede cargar el producto verificar");
		}
		
		return producto;
	}
}

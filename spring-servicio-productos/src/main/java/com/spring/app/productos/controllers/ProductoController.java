package com.spring.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.commons.models.entity.Producto;
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
	public Producto detalle(@PathVariable Long id)  {
		Producto producto = productoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port") ) );
		producto.setPort( port );
		
		//forzar un error para verificar hystrix
		/*boolean ok =false;
		if(ok == false) {
			throw new Exception ("No se puede cargar el producto verificar");
		}*/
		
		//Se pone un tiempo muerto para ver la tolerancia de tiempo fuera
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return producto;
	}
	
	@PostMapping("/crear") 
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		//Los parametros enviados en el json deben de coincidir con los atributos del objeto productos, el mapeo es automatico creando el objeto
		//Por JSON son pasados los datos del producto en el cuerpo del mensaje, es necesario
		//especificar que es un request
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);
		
		productoDb.setNombre(producto.getNombre() );
		productoDb.setPrecio(producto.getPrecio());
		
		return productoService.save(productoDb);
	}	
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //porque no devuelve nada solo elemina los datos se envia el codigo 204
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}

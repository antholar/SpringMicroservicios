package com.spring.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.app.item.models.Item;
import com.spring.app.item.models.Producto;
import com.spring.app.item.models.service.ItemService;

@RefreshScope
@RestController
public class ItemController {
	
	/*
	 * como se implemento dos servicios que llama a itemservice, spring no sabe cual tomar
	 * por lo que se debe de poner una etiqueta en el servicio que se quiere usar de primary 
	 * para que considere como servicio principal o sino usar la etiqueta Qualifier, llamando al
	 * servicio que se quiere implementar, sin usar la etiqueta Primary.
	 * Como buena practica, se usan los servicios la primera letra en minusculas y lo demas
	 * como esta en el nombre. Mirar el servicio ItemServiceFeign para ver el nombre del servicio
	 * */
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;  //variable para obtener los datos del archivo properties
	
	@Autowired
	@Qualifier("serviceFeign")
	//@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	//variable para obtener el texto de configuracion, del archivo GIT
	@Value("${configuracion.texto}")
	private String textoConfiguracion;
	
	
	@GetMapping("/listar")
	public List<Item> Listar(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo") //funcion alternativa en caso de error
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("camara barata");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
		
	}
	
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){  //otra forma de obtener datos del archivo de configuracion
		log.info(textoConfiguracion);
		
		Map<String,String> json = new HashMap<>();
		json.put("textoConfiguracion",textoConfiguracion);
		json.put("puerto",puerto);
		
		
		if(env.getActiveProfiles().length>0      //se verifica si se esta usando las configuraciones de profile
				&& env.getActiveProfiles()[0].equals("dev") ) {    //se verifica si el perfil esta en desarrollo, en cuanto al perfil de uso se puede usar mas de uno al mismo tiempo x eso es un array
			json.put("autor.nombre",env.getProperty("configuracion.autor.nombre") );
			json.put("autor.email",env.getProperty("configuracion.autor.email") );
		}	
		return new ResponseEntity<Map<String,String> >(json,HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	//se usa el requestbody para obtener la data del body
	public Producto crear(@RequestBody Producto producto) {
		return itemService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	//se usa el requestbody para obtener la data del body
	public Producto editar(@RequestBody Producto producto,@PathVariable Long id) {
		return itemService.update(producto,id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//se usa el requestbody para obtener la data del body
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}
	
	
}

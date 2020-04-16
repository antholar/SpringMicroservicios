package com.spring.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.app.item.models.Item;
import com.spring.app.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;
	
	public List<Item> findAll() {
		//List<Producto> productos = Arrays.asList( clienteRest.getForObject("http://localhost:8001/listar", Producto[].class ) ); //primero se pone el url y luego que tipo de datos se retornaran, como retorna un array se tiene q convertir
		List<Producto> productos = Arrays.asList( clienteRest.getForObject("http://servicio-productos/listar", Producto[].class ) );
		List<Item> respu = productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList()); //convertido apartir de apistream
		return respu;
	}

	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put( "id", id.toString() );
		//Producto producto = clienteRest.getForObject( "http://localhost:8001/ver/{id}", Producto.class, pathVariables  );
		Producto producto = clienteRest.getForObject( "http://servicio-productos/ver/{id}", Producto.class, pathVariables  );
		return new Item(producto,cantidad);
	}

}

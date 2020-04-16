package com.spring.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.spring.app.item.clientes.ProductoClienteRest;
import com.spring.app.item.models.Item;
import com.spring.app.item.models.Producto;

@Service("serviceFeign")
//@Primary  //sirve para indicar que servicio se debe de considerar 
public class ItemServiceFeign implements ItemService {

	//Al ser la clase productoClienteRest anotada con feign, ya se puede usar la notacion
	//Autowired porque ya ese servicio ya esta corriendo por debajo
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList()); //convertido apartir de apistream;
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id),cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}

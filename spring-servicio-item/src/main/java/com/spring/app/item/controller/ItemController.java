package com.spring.app.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.item.models.Item;
import com.spring.app.item.models.service.ItemService;

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
	@Autowired
	@Qualifier("serviceFeign")
	//@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> Listar(){
		return itemService.findAll();
	}
	
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
}

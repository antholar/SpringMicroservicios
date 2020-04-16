package com.spring.app.item.models.service;

import java.util.List;

import com.spring.app.item.models.Item;
import com.spring.app.item.models.Producto;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	public Producto update(Producto producto, Long id);
	public void delete(Long id);
	
}

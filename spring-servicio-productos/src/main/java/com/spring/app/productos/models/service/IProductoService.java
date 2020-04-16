package com.spring.app.productos.models.service;

import java.util.List;

import com.spring.app.commons.models.entity.Producto;

/*
 *	El servicio se puede utilizar para juntar muchos dao en una sola clase, ya que el servicio implementa todas las funciones del dao 
 *	El nombre de las funciones pueden ser distintas ya que se implementaran en otra clase que llamara a las funciones del crud
 * */
//
//
public interface IProductoService {
	
	//obtener la lista competa de los productos
	public List<Producto> findAll();
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	public void deleteById(long id);
	
}

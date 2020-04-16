package com.spring.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.productos.models.dao.ProductoDao;
import com.spring.app.productos.models.entity.Producto;


/*
 * Recordar que Service es una implementacion de component, guardandolo como un bean del spring, pudiendose generando inyeccion de dependencias
 * */
@Service   
public class ProductoServiceImpl implements IProductoService {

	//inyeccion de dependencias
	@Autowired
	private ProductoDao productoDao;
	
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		/*
		 * el findbyid regresa un optional en el que se le puede especificar con punto que puede devolver si es que da un error
		 * x eso se usa el orElse en ves del get en el caso que de un error
		 * */
		return productoDao.findById(id).orElse(null);  
	}

}

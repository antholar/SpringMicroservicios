package com.spring.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.app.commons.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {  //primero se especifica el entity, y el tipo de la llave
	//para el acceso a datos, la clase virtual crud ya tiene implementado todas las funciones de consultas a la base de datos
}

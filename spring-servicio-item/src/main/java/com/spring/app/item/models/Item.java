package com.spring.app.item.models;

import java.util.Date;

import com.spring.app.commons.models.entity.Producto;

public class Item {

	private Producto producto;
	private Integer cantidad;

	public Item() {
	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

	public Producto getProducto() {
		return producto;
	}

	public String getEstado() {
		return "regresa el estado";
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}

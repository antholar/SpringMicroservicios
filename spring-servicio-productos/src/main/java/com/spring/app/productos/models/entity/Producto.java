package com.spring.app.productos.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 6247833434079579141L;

	@Id //indicamos que es llave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //se establece la estrategia de como se generara el id si es autoincremental, unico,etc
	private Long id;
	
	private String nombre;
	private Double precio;
	
	@Column(name="create_at")    //se le puede especificar mas datos en la columna como el length, precision en caso q sea decimal, etc, todo separado por coma
	@Temporal(TemporalType.DATE)     //si se pone DATE por debajo combierte de un java.sql a un java.util
	private Date createAt;
	
	@Transient  //indica que no es un dato persistente y no es necesario mapearlo a la bd
	private Integer port;
	
	//////////////////////////////////
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}

package com.nagro.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="detalle_productos")
public class DetalleProducto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	private String detalle;
	
	@NotEmpty
	private String tipoDetalle;

	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDetalle() {
		return detalle;
	}



	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}



	public String getTipoDetalle() {
		return tipoDetalle;
	}



	public void setTipoDetalle(String tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
	}


	public Producto getProducto() {
		return producto;
	}



	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

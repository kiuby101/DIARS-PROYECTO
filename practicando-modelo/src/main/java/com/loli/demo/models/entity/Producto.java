package com.loli.demo.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String marca;	
	
	@NotEmpty
	private String codigo;

	@NotNull
	private float precio;
	
	@NotNull
	private int stock;
	

	@ManyToOne(fetch = FetchType.LAZY)
	private TipoProducto tipoProd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public TipoProducto getTipoProd() {
		return tipoProd;
	}

	public void setTipoProd(TipoProducto tipoProd) {
		this.tipoProd = tipoProd;
	}

	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	/*@OneToMany(mappedBy = "producto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<DetalleProducto> detalleProd;
		
	public List<DetalleProducto> getDetalleProd() {
		return detalleProd;
	}

	public void setDetalleProd(List<DetalleProducto> detalleProd) {
		this.detalleProd = detalleProd;
	}

	public void addDetalleProd(DetalleProducto details) {
		this.detalleProd.add(details);
	}
	
	/*@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	private DetalleProducto detalleProducto;
	

	public DetalleProducto getDetalleProducto() {
		return detalleProducto;
	}

	public void setDetalleProducto(DetalleProducto detalleProducto) {
		this.detalleProducto = detalleProducto;
	}*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

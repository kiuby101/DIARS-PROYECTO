package com.loli.demo.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Compras implements Serializable{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String medio_pago;
	
	private String medio_envio;
	
	private double total;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Carrito> carrito;
	
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public void addCarrito(Carrito carritos) {
		this.carrito.add(carritos);
	}
	
	public Compras() {
		this.carrito = new ArrayList<Carrito>();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedio_pago() {
		return medio_pago;
	}

	public void setMedio_pago(String medio_pago) {
		this.medio_pago = medio_pago;
	}

	public String getMedio_envio() {
		return medio_envio;
	}

	public void setMedio_envio(String medio_envio) {
		this.medio_envio = medio_envio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Carrito> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Carrito> carrito) {
		this.carrito = carrito;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

package com.loli.demo.models.entity;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//denota que sea requerido el campo
	@NotEmpty
	//el nombre estará entre 4 y 12 caracteres
	//@Size(min = 4 ,max=12)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	//denotar que sea correcto el formato email
	//importante que sea importado de javax.validation.constraints
	@Email
	@Column(unique = true)
	private String email;
	
	
	private String roles;
	
	private String password;

	//validar que sea diferente de nulo
	@NotNull
	@Column(name = "create_at")
	//denotar variable del tipo fecha
	@Temporal(TemporalType.DATE)
	//agregar formato de fecha
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	/*
	//notacion para que se llame el metodo justo antes de insertar el registro en la based
	@PrePersist
	//antes de que se guarde de la based
	public void prePersist() {
		createAt = new Date();
	}
	*/
	
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	private static final long serialVersionUID = 1L;

}

package com.nagro.springboot.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

<<<<<<< HEAD
@Entity
@Table(name = "administradores")
public class Administrador implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	private String mail;

	@NotEmpty
	private String password;

	@NotEmpty
	private String tipoUsuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
=======

@Entity
@Table(name = "administradores")
public class Administrador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String mail;

    @NotEmpty
    private String password;

    @NotEmpty
    private String tipoUsuario;




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }




    public String getMail() {
        return mail;
    }




    public void setMail(String mail) {
        this.mail = mail;
    }




    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }




    public String getTipoUsuario() {
        return tipoUsuario;
    }




    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }




    public static long getSerialversionuid() {
        return serialVersionUID;
    }




    /**
     * 
     */
    private static final long serialVersionUID = 1L;



}
>>>>>>> c1f81c65341806f7a0fbf8a079f79f0ef68ff961

package com.todo.todo.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQueries({ 
@NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuario u"),
@NamedQuery(name = "Usuarios.byLoginUsuario", query = "SELECT u FROM Usuario u where u.loginUsuario = :username"), 
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private String idUsuario;

	private String email;

	private Boolean estado;

	@Column(name="login_usuario")
	private String loginUsuario;

	private String nombre;

	private String password;

	public Usuario() {
	}
	
	@PrePersist
	public void initializeUUID() {
	    if (idUsuario == null) {
	    idUsuario = UUID.randomUUID().toString();
	    }
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
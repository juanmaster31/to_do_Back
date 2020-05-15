package com.todo.todo.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the tableros database table.
 * 
 */
@Entity
@Table(name="tableros")
@NamedQuery(name="Tablero.findAll", query="SELECT t FROM Tablero t")
public class Tablero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tablero")
	private String idTablero;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Tablero() {
	}
	
	@PrePersist
	public void initializeUUID() {
	    if (idTablero == null) {
	    	idTablero = UUID.randomUUID().toString();
	    }
	}

	public String getIdTablero() {
		return this.idTablero;
	}

	public void setIdTablero(String idTablero) {
		this.idTablero = idTablero;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
package com.todo.todo.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the actividades database table.
 * 
 */
@Entity
@Table(name="actividades")
@NamedQuery(name="Actividade.findAll", query="SELECT a FROM Actividade a")
public class Actividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_actividad")
	private String idActividad;

	private String descripcion;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	//bi-directional many-to-one association to EstadosActividad
	@ManyToOne
	@JoinColumn(name="id_estado_actividad")
	private EstadosActividad estadosActividad;

	//bi-directional many-to-one association to Tablero
	@ManyToOne
	@JoinColumn(name="id_tablero")
	private Tablero tablero;

	public Actividade() {
	}
	
	@PrePersist
	public void initializeUUID() {
	    if (idActividad == null) {
	    	idActividad = UUID.randomUUID().toString();
	    }
	}

	public String getIdActividad() {
		return this.idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public EstadosActividad getEstadosActividad() {
		return this.estadosActividad;
	}

	public void setEstadosActividad(EstadosActividad estadosActividad) {
		this.estadosActividad = estadosActividad;
	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

}
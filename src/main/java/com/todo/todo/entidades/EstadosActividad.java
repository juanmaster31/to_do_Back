package com.todo.todo.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados_actividad database table.
 * 
 */
@Entity
@Table(name="estados_actividad")
@NamedQuery(name="EstadosActividad.findAll", query="SELECT e FROM EstadosActividad e")
public class EstadosActividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estado_actividad")
	private Integer idEstadoActividad;

	private String descripción;

	public EstadosActividad() {
	}

	public Integer getIdEstadoActividad() {
		return this.idEstadoActividad;
	}

	public void setIdEstadoActividad(Integer idEstadoActividad) {
		this.idEstadoActividad = idEstadoActividad;
	}

	public String getDescripción() {
		return this.descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

}
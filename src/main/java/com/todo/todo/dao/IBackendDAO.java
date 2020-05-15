package com.todo.todo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface IBackendDAO {
	
	public EntityManager getEntityManager();
	public <T> void crearEntidad(T entidad);
	public <T> void editarEntidad(T entidad);
	public <T> void borrarEntidad(T entidad);
	public <T> T encontrarPorId(Class<T> claseEntidad, Serializable id);
	public List consultarPorNamedQuery(String namedQuery, Map<String, Object> params, int limiteResultados);
	public List consultarPorNamedQuery(String namedQuery, Map<String, Object> params);
	public List consultarPorNamedQuery(String namedQuery, List params, int limiteResultados);
	public List consultarPorNamedQuery(String namedQuery, List params);
	
	

}

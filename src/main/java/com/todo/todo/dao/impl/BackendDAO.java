package com.todo.todo.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.todo.todo.dao.IBackendDAO;

@TransactionManagement(TransactionManagementType.BEAN)
public class BackendDAO implements IBackendDAO {

	private static final Logger logger = LoggerFactory.getLogger(BackendDAO.class);
	private EntityManagerFactory entityManagerFactory;
	private static BackendDAO instance = null;

	private BackendDAO() {
		entityManagerFactory = Persistence.createEntityManagerFactory("backendPSU");
		logger.info(String.format("ENTITYMANAGER: %1$s CREADO ", entityManagerFactory.toString()));
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	protected Session getCurrentSession() {
		return entityManagerFactory.createEntityManager().unwrap(Session.class);
	}

	@Override
	public <T> void crearEntidad(T entidad) {
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			em.persist(entidad);
			em.flush();
			em.getTransaction().commit();
			em.close();
		} catch (HibernateException ex) {
			logger.error(
					String.format("Ocurrio un problema guardando la entidad: %1$s ", entidad.getClass().getName()));
		}
	}

	@Override

	public <T> void editarEntidad(T entidad) {
		logger.info("BaseDAO:editarEntidad:entidad={" + entidad.getClass() + "}");
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			em.merge(entidad);
			em.flush();
			em.getTransaction().commit();
			em.close();
		} catch (HibernateException ex) {
			logger.error(String.format("Ocurrio un problema editando la entidad: %1$s ", entidad.getClass().getName()));
		}
	}

	@Override

	public <T> void borrarEntidad(T entidad) {
		logger.info("BaseDAO:borrarEntidad:entidad={" + entidad.getClass() + "}");
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(entidad));
			em.flush();
			em.getTransaction().commit();
			em.close();
		} catch (HibernateException ex) {
			logger.error(String.format("Ocurrio un problema borrando la entidad: %1$s ", entidad.getClass().getName()));
		}
	}

	@Override
	public <T> T encontrarPorId(Class<T> claseEntidad, Serializable id) {
		T retorno = null;
		EntityManager em = getEntityManager();
		try {
			retorno = (T) em.find(claseEntidad, id);
		} catch (Exception ex) {
			logger.error(String.format("Ocurrio un problema encontrando la entidad: %1$s ", claseEntidad));
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return retorno;
	}

	@Override
	public List consultarPorNamedQuery(String namedQuery, Map<String, Object> params, int limiteResultados) {
		logger.info("BaseDAO:consultarPorNamedQuery:{namedQuery=" + namedQuery + "}");
		List retorno = null;
		EntityManager em = getEntityManager();
		try {
			Object campo = null;
			String key = null;
			Query query = em.createNamedQuery(namedQuery);
			if (params != null && !params.isEmpty()) {
				for (Map.Entry ent : params.entrySet()) {
					campo = ent.getValue();
					key = (String) ent.getKey();
					if (campo != null) {
						query.setParameter(key, campo);
					} else {
						query.setParameter(key, campo);
					}
				}
			}
			query.setMaxResults(limiteResultados);
			retorno = query.getResultList();
		} catch (HibernateException ex) {
			logger.error("Ocurrio un problema consultarPorNamedQuery:{namedQuery=" + namedQuery + "}");
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return retorno;
	}

	@Override
	public List consultarPorNamedQuery(String namedQuery, Map<String, Object> params) {
		return consultarPorNamedQuery(namedQuery, params, 0);
	}

	@Override
	public List consultarPorNamedQuery(String namedQuery, List params, int limiteResultados) {
		logger.info("BaseDAO:consultarPorNamedQuery:{namedQuery=" + namedQuery + "}");
		List retorno = null;
		EntityManager em = getEntityManager();
		try {
			Query query = em.createNamedQuery(namedQuery);
			if (params != null && !params.isEmpty()) {
				int i = 0;
				for (Object parametro : params) {
					query.setParameter(i, parametro);
					i++;
				}
			}
			query.setMaxResults(limiteResultados);
			retorno = query.getResultList();
		} catch (HibernateException e) {
			logger.error("Ocurrio un problema consultarPorNamedQuery:{namedQuery=" + namedQuery + "}");
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return retorno;
	}

	@Override
	public List consultarPorNamedQuery(String namedQuery, List params) {
		return consultarPorNamedQuery(namedQuery, params, 0);
	}

	public static BackendDAO getInstance() {
		if (instance == null) {
			instance = new BackendDAO();
		}
		return instance;
	}

}
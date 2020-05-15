package com.todo.todo.servicios.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.todo.todo.dao.IBackendDAO;
import com.todo.todo.dao.factory.DaoFactory;
import com.todo.todo.entidades.Usuario;
import com.todo.todo.servicios.IServicioUsuario;

import jakarta.ws.rs.core.Response;

public class ServicioUsuario  implements IServicioUsuario{
	private IBackendDAO dao = DaoFactory.create();
	private static final Logger logger = LoggerFactory.getLogger(ServicioUsuario.class);
	@Override
	public Response autenticarUsuario(String username, String password) {
	try {
	Map<String, Object> params = new HashMap<>();
	params.put("username", username);
	List<Usuario> usuarios = (List<Usuario>) dao.consultarPorNamedQuery("Usuarios.byLoginUsuario", params);
	Usuario usuario = null;
	if(!usuarios.isEmpty()) {
	usuario = usuarios.get(0);
	}
	if(usuario == null) {
	return Response.noContent().build();
	}else {
	if(usuario.getPassword().equals(password)) {
	return Response.ok().build();
	}else {
	return Response.status(401).build();
	}
	}
	}catch(Exception e) {
	logger.error("Error autenticado el usuario. ");
	e.printStackTrace();
	}
	return Response.serverError().build();
	}

	@Override
	public Response crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

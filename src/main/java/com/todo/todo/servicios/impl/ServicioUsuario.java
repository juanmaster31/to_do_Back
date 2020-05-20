package com.todo.todo.servicios.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.todo.todo.dao.IBackendDAO;
import com.todo.todo.dao.factory.DaoFactory;
import com.todo.todo.entidades.Usuario;
import com.todo.todo.entidades.dto.LoginDTO;
import com.todo.todo.request.CreateRequest;
import com.todo.todo.servicios.IServicioUsuario;

import jakarta.ws.rs.core.Response;

public class ServicioUsuario implements IServicioUsuario {
	private IBackendDAO dao = DaoFactory.create();
	private static final Logger logger = LoggerFactory.getLogger(ServicioUsuario.class);

	@Override
	public Response autenticarUsuario(String username, String password) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("username", username.toLowerCase());
			List<Usuario> usuarios = (List<Usuario>) dao.consultarPorNamedQuery("Usuarios.byLoginUsuario", params);
			Usuario usuario = null;
			if (!usuarios.isEmpty()) {
				usuario = usuarios.get(0);
			}
			if (usuario == null) {
				return Response.noContent().build();
			} else {
				if (usuario.validarPassword(password)) {
					LoginDTO login = new LoginDTO();
					login.setIdusuario(usuario.getIdUsuario());
					login.setEmail(usuario.getEmail());
					login.setNombre(usuario.getNombre());
					return Response.ok().entity(login).build();
				} else {
					return Response.status(401).build();
				}
			}
		} catch (Exception e) {
			logger.error("Error autenticado el usuario. ");
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

	@Override
	public Response crearUsuario(CreateRequest request) {
		try {
			if(existeUsuario(request.getLoginusuario().toLowerCase(), request.getEmail().toLowerCase())){
				return Response.notModified().build();
			}
			Usuario usuario = new Usuario();	
			usuario.setLoginUsuario(request.getLoginusuario().toLowerCase());
			usuario.setPassword(request.getPassword());
			usuario.setNombre(request.getNombre().toUpperCase());
			usuario.setEmail(request.getEmail().toLowerCase());
			usuario.setEstado(true);
			dao.crearEntidad(usuario);
			return Response.ok().build();
			
		}catch(Exception e) {
			logger.error("Error registrando usuario. ");
			e.printStackTrace();
		}
		return Response.serverError().build();
		
	}
	
	private boolean existeUsuario(String loginusuario, String email) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("loginusuario", loginusuario);
			params.put("email", email);
			List<Usuario> usuarios = (List<Usuario>) dao.consultarPorNamedQuery("Usuarios.usuarioByLoginEmail", params);
			if(usuarios.isEmpty()) {
				return false;				
			}
		} catch(Exception e) {
			logger.error("Error consultando usuario. ");
			e.printStackTrace();	
			
		}
		return true;
	}

	@Override
	public Response actualizarUsuario(String idusuario) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("idusuario", idusuario);
			List<Usuario> usuarios = (List<Usuario>) dao.consultarPorNamedQuery("Usuarios.updateByIdUsuario", params);
			Usuario usuario = null;
			if (!usuarios.isEmpty()) {
				usuario = usuarios.get(0);
			}
			if (usuario == null) {
				return Response.noContent().build();
			} else {
				if (usuario.getIdUsuario().equals(idusuario)) {
					return Response.ok().build();
				} else {
					return Response.status(401).build();
				}
			}
		} catch (Exception e) {
			logger.error("Error al actualizar el usuario. ");
			e.printStackTrace();
		}
		return Response.serverError().build();
	}

}

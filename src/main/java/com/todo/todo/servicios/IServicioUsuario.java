package com.todo.todo.servicios;

import com.todo.todo.entidades.Usuario;

import jakarta.ws.rs.core.Response;

public interface IServicioUsuario {
	public Response autenticarUsuario(String username, String password);
	public Response crearUsuario(Usuario usuario);
	public Response actualizarUsuario(Usuario usuario);	

}

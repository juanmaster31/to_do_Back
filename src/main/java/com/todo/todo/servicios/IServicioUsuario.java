package com.todo.todo.servicios;

import com.todo.todo.entidades.Usuario;
import com.todo.todo.request.CreateRequest;

import jakarta.ws.rs.core.Response;

public interface IServicioUsuario {
	public Response autenticarUsuario(String username, String password);
	public Response crearUsuario(CreateRequest request);
	public Response actualizarUsuario(String idusuario);	

}

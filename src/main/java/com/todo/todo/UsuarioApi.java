package com.todo.todo;

import com.todo.todo.request.AuthRequest;
import com.todo.todo.request.CreateRequest;
import com.todo.todo.request.UpdateRequest;
import com.todo.todo.servicios.IServicioUsuario;
import com.todo.todo.servicios.impl.ServicioUsuario;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioApi {
	private IServicioUsuario _servicio = new ServicioUsuario();
	
	@POST
	@Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(AuthRequest request) {
    return this._servicio.autenticarUsuario(request.getUsername(), request.getPassword());      
    }
    
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(CreateRequest request) {
    return this._servicio.crearUsuario(request);
    }
    
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(UpdateRequest request) {
    return this._servicio.actualizarUsuario(request.getIdusuario());
    }

}

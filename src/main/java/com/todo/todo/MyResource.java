package com.todo.todo;

import com.todo.todo.dao.IBackendDAO;
import com.todo.todo.dao.factory.DaoFactory;
import com.todo.todo.entidades.Usuario;
import com.todo.todo.request.AuthRequest;
import com.todo.todo.servicios.IServicioUsuario;
import com.todo.todo.servicios.impl.ServicioUsuario;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("usuarios")
public class MyResource {
	private IServicioUsuario _servicio;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(AuthRequest request) {
    this._servicio = new ServicioUsuario();
    return this._servicio.autenticarUsuario(request.getUsername(), request.getPassword());   
    
    }
}

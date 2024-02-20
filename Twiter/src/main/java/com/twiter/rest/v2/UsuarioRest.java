package com.twiter.rest.v2;

import com.twiter.accesodatos.UsuarioAccesoDatos;
import com.twiter.entidades.Usuario;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/usuarios")
public class UsuarioRest {

	@GET
	@Path("/{nickName}")
	public Usuario buscarNickName(@PathParam("nickName") String nickName) {
		Usuario usuario = UsuarioAccesoDatos.buscarPorNickName(nickName);
		
		if(usuario == null) {
			throw new NotFoundException();
		}
		
		return usuario;
	}
	
}

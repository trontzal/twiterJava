package com.twiter.rest.v2;

import com.twiter.Dtos.RolDTO;
import com.twiter.Dtos.UsuarioDTO;
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
	public UsuarioDTO buscarNickName(@PathParam("nickName") String nickName) {
		Usuario usuario = UsuarioAccesoDatos.buscarPorNickName(nickName);
		
		if(usuario == null) {
			throw new NotFoundException();
		}
		
		RolDTO rolDto = new RolDTO(usuario.getRol().getId(), usuario.getRol().getNombre());
		
		return new UsuarioDTO(usuario.getId(), usuario.getNickName(), usuario.getContrasena(), rolDto);
	}
	
}

package com.twiter.rest.v2;

import java.util.List;

import com.twiter.Dtos.PostDTO;
import com.twiter.accesodatos.PostAccesoDatos;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/posts")
public class PostRest {
	
	@GET
	public List<PostDTO> obtenerTodos(){
		return PostAccesoDatos.obtenerTodos();
	}
}

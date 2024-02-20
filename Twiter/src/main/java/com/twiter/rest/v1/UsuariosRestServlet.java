package com.twiter.rest.v1;

import java.io.IOException;
import java.io.PrintWriter;


import com.twiter.accesodatos.UsuarioAccesoDatos;
import com.twiter.entidades.Usuario;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/usuarios/*")
public class UsuariosRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Jsonb json = JsonbBuilder.create();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String path = request.getPathInfo();
		
		if(path != null && path.trim().length() != 1) {
			String nickName = path.replace("/", "");
			
			
			Usuario usuario = UsuarioAccesoDatos.buscarPorNickName(nickName);
			System.out.println(usuario);
						
			if(usuario != null) {
				out.println(json.toJson(usuario));
			}else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			
			return;
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
	
}
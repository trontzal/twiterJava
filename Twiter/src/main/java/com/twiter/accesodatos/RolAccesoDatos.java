package com.twiter.accesodatos;

import com.twiter.entidades.Rol;

public class RolAccesoDatos {
	public static void insertar(Rol rol) {
		AccesoDatos.enTransaccion(em ->{
			em.persist(rol);
			return null;
		});
	}
	
	public static Rol obtenerPorId(long id) {
		System.err.println("\n ObtenerPorIdRol");
		return AccesoDatos.enTransaccion(em -> em.find(Rol.class, id));
	}
	
}

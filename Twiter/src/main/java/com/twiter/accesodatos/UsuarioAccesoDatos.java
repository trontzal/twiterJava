package com.twiter.accesodatos;

import static com.twiter.accesodatos.AccesoDatos.enTransaccion;

import java.util.List;

import com.twiter.entidades.Usuario;

import jakarta.persistence.Query;

public class UsuarioAccesoDatos {
	public static void insertar(Usuario usuario) {
		enTransaccion(em -> {
			em.persist(usuario);
			return null;
		});
	}

	public static Usuario buscarPorNickName(String nickName) {
		return enTransaccion(em -> {
			System.err.println("\n BuscarPorNickName");
			var usuario = em.createQuery(
					"select usuario from Usuario as usuario join fetch usuario.rol where usuario.nickName = :nickName",
					Usuario.class).setParameter("nickName", nickName).getResultList();
			if (usuario.size() == 1) {
				return usuario.get(0);
			} else {
				return null;
			}
		});
	}

	public static void agregarSeguidor(long id, long seguirA) {
		enTransaccion(em -> {
			Query query = em
					.createNativeQuery("INSERT INTO seguidores (usuario_id, seguidor_de_id) VALUES (:id, :seguirA)");

			query.setParameter("id", id);
			query.setParameter("seguirA", seguirA);
			query.executeUpdate();

			return null;
		});
	}

	public static void dejarDeSeguir(long id, long dejarDeSeguirA) {
		enTransaccion(em -> {
			Query query = em
					.createNativeQuery("DELETE FROM seguidores AS s WHERE (s.seguidor_de_id = :dejarDeSeguirA) and (s.usuario_id = :id);");

			query.setParameter("id", id);
			query.setParameter("dejarDeSeguirA", dejarDeSeguirA);
			query.executeUpdate();

			return null;
		});
	}

	public static List<String> verSeguidores(long id) {
		System.err.println("\n Ver seguidores de un usuario");

		List<String> seguidores = enTransaccion(em -> {
			Query query = em.createNativeQuery(
					"SELECT u.nick_name FROM usuarios AS u WHERE u.id IN (SELECT s.usuario_id AS seguidores FROM usuarios AS u JOIN seguidores AS s ON u.id = s.seguidor_de_id WHERE u.id = :id)");
			query.setParameter("id", id);

			@SuppressWarnings("unchecked")
			List<String> resultList = query.getResultList();

			return resultList;
		});

		return seguidores;
	}
	
	public static List<String> verSeguidos(long id) {
		System.err.println("\n Ver seguidos de un usuario");
		
		List<String> seguidores = enTransaccion(em -> {
			Query query = em.createNativeQuery(
					"SELECT u.nick_name FROM usuarios AS u WHERE u.id IN(SELECT s.seguidor_de_id AS sigue_a FROM usuarios AS u JOIN seguidores AS s ON u.id = s.usuario_id where s.usuario_id = :id)");
			query.setParameter("id", id);
			
			@SuppressWarnings("unchecked")
			List<String> resultList = query.getResultList();
			
			return resultList;
		});
		
		return seguidores;
	}

}

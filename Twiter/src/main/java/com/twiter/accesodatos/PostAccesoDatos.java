package com.twiter.accesodatos;

import static com.twiter.accesodatos.AccesoDatos.enTransaccion;

import java.util.List;

import com.twiter.entidades.Post;

import jakarta.persistence.Query;

public class PostAccesoDatos {
	public static void insert(Post post) {
		enTransaccion(em -> {
			em.persist(post);
			return null;
		});
	}

	public static List<Post> obtenerTodos() {
		System.err.println("\n obtenerTodosLosPosts");
		return enTransaccion(em -> em.createQuery(
				"select post from Post as post join fetch post.usuario join fetch usuario.rol order by post.fecha desc",
				Post.class).getResultList());
	}

	public static List<Post> obtenerPorIdUsuario(long id) {
		System.err.println("\n Obtener posts por usuario id");
		return enTransaccion(em -> em.createQuery(
				"select post from Post as post join fetch post.usuario join fetch usuario.rol where post.usuario.id = :id order by post.fecha desc",
				Post.class).setParameter("id", id).getResultList());
	}

	
	// mal
	public static List<Object> obtenerPostsDeSeguidos(long id){
		System.err.println("\n Obtener todos los posts de los siguiendos");
		
		List<Object> postSeguidos = enTransaccion(em ->{
			Query query = em.createNativeQuery(
					"SELECT p.fecha, u.nick_name, p.texto FROM posts AS p JOIN usuarios AS u ON p.usuario_id = u.id WHERE u.id IN (SELECT s.seguidor_de_id AS sigue_a FROM usuarios AS u JOIN seguidores AS s ON u.id = s.usuario_id WHERE s.usuario_id = :id) ORDER BY p.fecha;");
			query.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<Object> resultList = query.getResultList();

			return resultList;
		});
		
		return postSeguidos;
	}
}

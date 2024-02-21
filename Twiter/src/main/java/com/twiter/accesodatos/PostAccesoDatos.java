package com.twiter.accesodatos;

import static com.twiter.accesodatos.AccesoDatos.enTransaccion;

import java.util.List;

import com.twiter.Dtos.PostDTO;
import com.twiter.entidades.Post;

import jakarta.persistence.Query;

public class PostAccesoDatos {
	public static void insert(Post post) {
		enTransaccion(em -> {
			em.persist(post);
			return null;
		});
	}

	@SuppressWarnings("unchecked")
	public static List<PostDTO> obtenerTodos() {
	    System.err.println("\n obtenerTodosLosPosts");
	    return enTransaccion(em -> em.createNativeQuery("""
	                            SELECT
	                                p.id,
	                                u.nick_name AS usuario,
	                                p.texto,
	                                COUNT(pr.usuario_id) AS numeroRetweets,
	                                p.fecha
	                            FROM
	                                posts p
	                                    JOIN
	                                usuarios u ON p.usuario_id = u.id
	                                    LEFT JOIN
	                                posts_retwiteados pr ON p.id = pr.post_id
	                            GROUP BY p.id, u.nick_name, p.texto, p.fecha
	                            ORDER BY p.id
	                            """, PostDTO.class).getResultList());
	}



	public static List<Post> obtenerPorIdUsuario(long id) {
		System.err.println("\n Obtener posts por usuario id");
		return enTransaccion(em -> em.createQuery(
				"select post from Post as post join fetch post.usuario join fetch usuario.rol where post.usuario.id = :id order by post.fecha desc",
				Post.class).setParameter("id", id).getResultList());
	}

	// mal
	public static List<Object> obtenerPostsDeSeguidos(long id) {
		System.err.println("\n Obtener todos los posts de los siguiendos");

		List<Object> postSeguidos = enTransaccion(em -> {
			Query query = em.createNativeQuery("");
			query.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<Object> resultList = query.getResultList();

			return resultList;
		});

		return postSeguidos;
	}
}

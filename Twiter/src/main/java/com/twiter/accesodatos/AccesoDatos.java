package com.twiter.accesodatos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AccesoDatos {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("com.twiter.entidades");

	static {
		AccesoDatos.enTransaccion(em -> {
			return null;
		});
	}

	public static <T> T enTransaccion(Function<EntityManager, T> lamba) {
		T resultado;

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			resultado = lamba.apply(entityManager);
			transaction.commit();

			return resultado;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
}

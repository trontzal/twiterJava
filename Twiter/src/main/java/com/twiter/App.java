package com.twiter;

import com.twiter.accesodatos.PostAccesoDatos;
import com.twiter.accesodatos.RolAccesoDatos;
import com.twiter.accesodatos.UsuarioAccesoDatos;
import com.twiter.entidades.Post;
import com.twiter.entidades.Rol;
import com.twiter.entidades.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {

//	public static void main(String[] args) {
//		Rol rol2 = RolAccesoDatos.obtenerPorId(2);
//		System.out.println(rol2);
//	}
	
	public static void main(String[] args) {
		// roles para que se puedan crear usuarios
		RolAccesoDatos.insertar(new Rol(null, "ADMIN"));
		RolAccesoDatos.insertar(new Rol(null, "USER"));
		
		// obtener el rol para poder usarlo al crear el usuario
		Rol rolAdmin = RolAccesoDatos.obtenerPorId(1);
		Rol rolUser = RolAccesoDatos.obtenerPorId(2);
		
		//registro (insert usuario)
		UsuarioAccesoDatos.insertar(new Usuario("gonzalo", "contra", rolAdmin));
		UsuarioAccesoDatos.insertar(new Usuario("pepe", "pepe", rolUser));
		UsuarioAccesoDatos.insertar(new Usuario("juan", "juan", rolUser));
		
		// loggeo (buscar el usuario)
		Usuario gonzalo = UsuarioAccesoDatos.buscarPorNickName("gonzalo");
		Usuario pepe = UsuarioAccesoDatos.buscarPorNickName("pepe");
		Usuario juan = UsuarioAccesoDatos.buscarPorNickName("juan");
		
		// gestion de posts
		Post primerPost = new Post(gonzalo, "Primer post de mi aplicacion de twiter");
		Post segundoPost = new Post(pepe, "Segundo post de mi aplicacion de twiter por pepe");
		PostAccesoDatos.insert(primerPost);
		PostAccesoDatos.insert(segundoPost);
		var posts = PostAccesoDatos.obtenerTodos();
		var postsGonzalo = PostAccesoDatos.obtenerPorIdUsuario(gonzalo.getId());
		var postsSeguidos = PostAccesoDatos.obtenerPostsDeSeguidos(3);
		
		
		// seguimientos
		UsuarioAccesoDatos.agregarSeguidor(pepe.getId(), gonzalo.getId());
		UsuarioAccesoDatos.agregarSeguidor(juan.getId(), gonzalo.getId());
		UsuarioAccesoDatos.agregarSeguidor(juan.getId(), pepe.getId());
		UsuarioAccesoDatos.dejarDeSeguir(pepe.getId(), gonzalo.getId());
		var seguidoresGonzalo = UsuarioAccesoDatos.verSeguidores(gonzalo.getId());
		var segidosJuan = UsuarioAccesoDatos.verSeguidos(juan.getId());
//		var verTodosLosUsuarios = UsuarioAccesoDatos.obtenerTodos();
		
		
		// Salidas de consola ------------------------------------------------
		System.out.println("\nVer usuario gonzalo");
		System.out.println(gonzalo);

		System.out.println("\nVer todos los posts");
		System.out.println(posts);
		
		System.out.println("\nVer Posts gonzalo");
		System.out.println(postsGonzalo);

		System.out.println("\nVer seguidores gonzalo");
		System.out.println(seguidoresGonzalo);
		
		System.out.println("\nVer seguidos de pepe");
		System.out.println(segidosJuan);
		
		// NO funciona
		System.out.println("\nVer post de seguidos de pepe");
		System.out.println(postsSeguidos);
		
		System.out.println("\nVer todos los usuarios");
//		System.out.println(verTodosLosUsuarios);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void mainAntiguo(String[] args) {
		// Iniciar la sesión de Hibernate
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TwiterApp");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        
        t.begin();
        
        // Cerrar la sesión de Hibernate
        em.close();
        emf.close();
	}

}

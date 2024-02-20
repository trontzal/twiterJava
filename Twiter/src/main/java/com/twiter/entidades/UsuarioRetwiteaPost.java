package com.twiter.entidades;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UsuarioRetwiteaPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@Column(name = "fecha")
	private LocalDate fecha;

	public UsuarioRetwiteaPost() {
		super();
	}

	public UsuarioRetwiteaPost(Long id, Usuario usuario, Post post, LocalDate fecha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.post = post;
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "UsuarioRetwiteaPost [id=" + id + ", usuario=" + usuario + ", post=" + post + ", fecha=" + fecha + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, id, post, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRetwiteaPost other = (UsuarioRetwiteaPost) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id) && Objects.equals(post, other.post)
				&& Objects.equals(usuario, other.usuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}

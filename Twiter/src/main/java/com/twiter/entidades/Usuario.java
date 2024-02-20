package com.twiter.entidades;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nick_name", nullable = false, length = 100)
	private String nickName;
	
	@Column(name = "contrasena", nullable = false)
	private String contrasena;

	@ManyToOne
	@JoinColumn(name = "roles_id", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
	private Rol rol;

	@ManyToMany
	@JoinTable(name = "posts_retwiteados", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "post_id"), foreignKey = @ForeignKey(name = "FK_post_retwiteado_por_usuario"), inverseForeignKey = @ForeignKey(name = "FK_usuario_retwitea_post"))
	private Set<Post> aRetwiteado;

	@ManyToMany
	@JoinTable(name = "seguidores", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "seguidor_de_id"))
	private Set<Usuario> seguidorDe;

	public Usuario() {
	}

	public Usuario(String nickName, String contrasena, Rol rol) {
		super();
		this.nickName = nickName;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public Usuario(Long id, String nickName, String contrasena, Rol rol, Set<Post> aRetwiteado,
			Set<Usuario> seguidorDe) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.contrasena = contrasena;
		this.rol = rol;
		this.aRetwiteado = aRetwiteado;
		this.seguidorDe = seguidorDe;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aRetwiteado, contrasena, id, nickName, rol, seguidorDe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(aRetwiteado, other.aRetwiteado) && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(id, other.id) && Objects.equals(nickName, other.nickName)
				&& Objects.equals(rol, other.rol) && Objects.equals(seguidorDe, other.seguidorDe);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Post> getaRetwiteado() {
		return aRetwiteado;
	}

	public void setaRetwiteado(Set<Post> aRetwiteado) {
		this.aRetwiteado = aRetwiteado;
	}

	public Set<Usuario> getSeguidorDe() {
		return seguidorDe;
	}

	public void setSeguidorDe(Set<Usuario> seguidorDe) {
		this.seguidorDe = seguidorDe;
	}
	
}
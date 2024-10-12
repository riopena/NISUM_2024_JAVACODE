package com.nisum.java.users_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity(name = "user_details")
public class User implements UserDetails{

	protected User() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	private Integer idUsuario;

	private UUID id;
	@NotNull(message = "Debe ingresar un nombre")
	@NotEmpty(message = "El nombre no debe estar vacio")
	private String name;
	@JsonIgnore
	private String username;

	@NotNull(message = "El email debe ser valido, ejemplo: aaaaaaa@dominio.cl")
	@NotEmpty(message = "El email debe ser valido, ejemplo: aaaaaaa@dominio.cl")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "El email debe ser valido, ejemplo estructura: aaaaaaa@dominio.cl")
	@Column(unique=true)
	private String email;

	@NotNull(message = "La password debe ser valida")
	@NotEmpty(message = "La password debe ser valida")
	private String password;

	private LocalDate createdDate;

	private LocalDateTime lastlogin;

	private LocalDate modified;

	private boolean isActive;
	
	private String token;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone> phones;

	public User(Integer idUsuario, UUID id, @NotNull String name, @NotNull String email, @NotNull String password,
			LocalDate createdDate, LocalDateTime lastlogin, LocalDate modified, boolean isActive, String token) {
		super();
		this.idUsuario = idUsuario;
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.lastlogin = lastlogin;
		this.modified = modified;
		this.isActive = isActive;
		this.token = token;
		this.username=name;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return name;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(LocalDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}

	public LocalDate getModified() {
		return modified;
	}

	public void setModified(LocalDate modified) {
		this.modified = modified;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "User [idUsuario=" + idUsuario + ", id=" + id + ", name=" + name + ", email=" + email + ", password="
				+ password + ", createdDate=" + createdDate + ", lastlogin=" + lastlogin + ", modified=" + modified
				+ ", isActive=" + isActive + ", token=" + token + ", phones=" + phones + "]";
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

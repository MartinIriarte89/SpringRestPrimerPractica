package com.martiniriarte.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;
	
	private String telephone;

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	private boolean role;

	@OneToMany
	@JoinColumn(name = "id_user")
	private List<Role> roles;

	public User(Long id, String name, String lastName, String telephone, String email) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
	}

	public User() {
	}
}

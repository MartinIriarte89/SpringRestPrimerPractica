package com.martiniriarte.models;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastName;
	private String telephone;
	private String email;
	private String password;
	private boolean role;
	
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

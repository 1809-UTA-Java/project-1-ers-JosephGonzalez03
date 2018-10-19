package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ers_user_roles")
public class UserRole {
	@Id
	@Column(name="ur_id")
	private int id;
	@Column(name="ur_role")
	private String role;

	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public UserRole() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}

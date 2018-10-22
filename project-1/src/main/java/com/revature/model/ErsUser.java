package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.service.CompanyEmployeeUser;

@Entity
@Table(name="ers_users")
public class ErsUser implements User {
	@Override
	public boolean equals(Object obj) {
		return (this.id == ((ErsUser) obj).getId() && 
				this.username.contentEquals(((ErsUser) obj).getUsername()));
	}

	@Id
	@Column(name="u_id")
	private int id;
	@Column(name="u_username")
	private String username;
	@Column(name="u_password")
	private String password;
	@Column(name="u_firstname")
	private String firstName;
	@Column(name="u_lastname")
	private String lastName;
	@Column(name="u_email")
	private String email;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="ur_id")
	private UserRole userRole;

	public ErsUser(int id, String username, String password, String firstName, String lastName,
			String email, UserRole userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public ErsUser(CompanyEmployeeUser e) {
		this.id = e.getId();
		this.password = e.getPassword();
		this.firstName = e.getFirstName();
		this.lastName = e.getLastName();
		this.email = e.getEmail();
		this.userRole = e.getUserRole();
	}
	
	public ErsUser() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean login(String username, String password) {
		return (this.username.contentEquals(username) && this.password.contentEquals(password));
	}

	public boolean viewHomePage() {
		// TODO Auto-generated method stub
		return false;
	}

	public User logout() {
		// TODO Auto-generated method stub
		return null;
	}
}

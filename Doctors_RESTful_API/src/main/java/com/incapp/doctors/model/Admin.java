package com.incapp.doctors.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * 
 */
@Entity
public class Admin {
	@Id
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
}

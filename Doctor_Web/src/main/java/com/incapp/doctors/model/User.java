package com.incapp.doctors.model;


import java.sql.Date;
import java.util.Arrays;

import org.springframework.stereotype.Component;
@Component
public class User {
	private String email;
	private String password;
	private String name;
	private String phone;
	private Date dob;
	private String gender;
    private byte[] photo;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", phone=" + phone + ", dob="
				+ dob + ", gender=" + gender + ", photo=" + Arrays.toString(photo) + "]";
	}
}

package com.incapp.doctors.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class DoctorNotAvail {
	private int id;
	
	private String email;
	private Date doc_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(Date doc_date) {
		this.doc_date = doc_date;
	}
	@Override
	public String toString() {
		return "DoctorNotAvail [id=" + id + ", email=" + email + ", doc_date=" + doc_date + "]";
	}
}

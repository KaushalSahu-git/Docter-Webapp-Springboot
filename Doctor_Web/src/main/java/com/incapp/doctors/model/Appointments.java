package com.incapp.doctors.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Appointments {

	private int id;
	private String doctor_email;
	private String user_email;
	private String name;
private String status;
	
	private Date doc_booking_date;
	private String doc_booking_time;
	
	private Date booking_date_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoctor_email() {
		return doctor_email;
	}
	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Date getDoc_booking_date() {
		return doc_booking_date;
	}
	public void setDoc_booking_date(Date doc_booking_date) {
		this.doc_booking_date = doc_booking_date;
	}
	public String getDoc_booking_time() {
		return doc_booking_time;
	}
	public void setDoc_booking_time(String doc_booking_time) {
		this.doc_booking_time = doc_booking_time;
	}
	public Date getBooking_date_time() {
		return booking_date_time;
	}
	public void setBooking_date_time(Date booking_date_time) {
		this.booking_date_time = booking_date_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Appointments [id=" + id + ", doctor_email=" + doctor_email + ", user_email=" + user_email + ", name="
				+ name + ", status=" + status + ", doc_booking_date=" + doc_booking_date + ", doc_booking_time="
				+ doc_booking_time + ", booking_date_time=" + booking_date_time + "]";
	}
	
}

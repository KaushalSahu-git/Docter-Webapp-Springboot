package com.incapp.doctors.model;


import java.sql.Date;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
public class Doctor {
	@Id
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(length = 10)
	private String phone;
	private Date dob;
	private String speciality;
	private String qualification;
	@Column(nullable = true)
	private int experience;
	private String gender;
	private String state;
	private String city;
	private String area;
	@Lob
    private byte[] photo;
	
	@OneToOne
	private DoctorAvail doctorAvail;
	
	public DoctorAvail getDoctorAvail() {
		return doctorAvail;
	}
	public void setDoctorAvail(DoctorAvail doctorAvail) {
		this.doctorAvail = doctorAvail;
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
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Doctor [email=" + email + ", password=" + password + ", name=" + name + ", phone=" + phone + ", dob="
				+ dob + ", speciality=" + speciality + ", qualification=" + qualification + ", experience=" + experience
				+ ", gender=" + gender + ", state=" + state + ", city=" + city + ", area=" + area + ", photo="
				+ Arrays.toString(photo) + ", doctorAvail=" + doctorAvail + "]";
	}
	
}

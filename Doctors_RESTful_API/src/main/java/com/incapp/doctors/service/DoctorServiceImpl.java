package com.incapp.doctors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.doctors.dao.DoctorDAO;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;

import jakarta.transaction.Transactional;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorDAO doctorDAO;
	
	@Transactional
	@Override
	public void save(Doctor doctor) {
		doctorDAO.save(doctor);
	}
	@Transactional
	@Override
	public void updateDocAvail(String email,DoctorAvail doctorAvail) {
		doctorDAO.updateDocAvail(email,doctorAvail);
	}

	@Override
	public List<Doctor> get() {
		return doctorDAO.get();
	}

	@Override
	public List<Doctor> get(String name) {
		return doctorDAO.get(name);
	}
	
	@Override
	public List<Doctor> get(String state,String city) {
		return doctorDAO.get(state,city);
	}

	@Override
	public List<Doctor> getBySpeciality(String speciality) {
		return doctorDAO.getBySpeciality(speciality);
	}
	@Override
	public Doctor getByEmail(String email) {
		return doctorDAO.getByEmail(email);
	}
	
	@Transactional
	@Override
	public boolean addDocNotAvail(DoctorNotAvail doctorNotAvail) {
		return doctorDAO.addDocNotAvail(doctorNotAvail);
	}
	@Override
	public List<DoctorNotAvail> getDocNotAvail(String email) {
		return doctorDAO.getDocNotAvail(email);
	}
	
	@Override
	public Doctor login(String email, String password) {
		return doctorDAO.login(email, password);
	}
	
	@Transactional
	@Override
	public void updateDoctor(Doctor doctor) {
		doctorDAO.updateDoctor(doctor);
	}
}

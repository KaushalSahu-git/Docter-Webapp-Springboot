package com.incapp.doctors.service;

import java.util.List;

import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;

public interface DoctorService {
	public void save(Doctor doctor) ;
	public void updateDoctor(Doctor doctor) ;
	public void updateDocAvail(String email,DoctorAvail doctorAvail);
	public List<Doctor> get() ;
	public List<Doctor> get(String name) ;
	public List<Doctor> get(String state,String city);
	public List<Doctor> getBySpeciality(String speciality);
	public Doctor getByEmail(String email) ;
	public boolean addDocNotAvail(DoctorNotAvail doctorNotAvail);
	public List<DoctorNotAvail> getDocNotAvail(String email) ;
	public Doctor login(String email,String password) ;
}

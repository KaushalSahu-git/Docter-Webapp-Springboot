package com.incapp.doctors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.doctors.dao.AppointmentDAO;
import com.incapp.doctors.model.Appointments;

import jakarta.transaction.Transactional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentDAO appointmentDAO;

	@Override
	public List<Appointments> getByUserEmail(String userEmail) {
		return appointmentDAO.getByUserEmail(userEmail);
	}

	@Override
	public List<Appointments> getByDoctorEmail(String doctorEmail) {
		return appointmentDAO.getByDoctorEmail(doctorEmail);
	}
	
	@Transactional
	@Override
	public boolean delete(int id) {
		return appointmentDAO.delete(id);
	}

	@Transactional
	@Override
	public String addAppointment(Appointments appointment) {
		return appointmentDAO.addAppointment(appointment);
	}
	
	
	
}

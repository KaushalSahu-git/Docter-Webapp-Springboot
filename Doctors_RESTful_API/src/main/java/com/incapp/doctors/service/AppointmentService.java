package com.incapp.doctors.service;

import java.util.List;

import com.incapp.doctors.model.Appointments;

public interface AppointmentService {
	public List<Appointments> getByUserEmail(String userEmail);
	public List<Appointments> getByDoctorEmail(String doctorEmail);
	public boolean delete(int id) ;
	public String addAppointment(Appointments appointment);
}

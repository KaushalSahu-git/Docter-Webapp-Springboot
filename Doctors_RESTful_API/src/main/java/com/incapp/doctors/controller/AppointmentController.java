package com.incapp.doctors.controller;

import org.springframework.web.bind.annotation.RestController;

import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.service.AppointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@DeleteMapping("/deleteAppointment/{id}")
	public boolean deleteAppointment(@PathVariable int id) {
		return appointmentService.delete(id);
	}
	@GetMapping("/getByUserEmail/{email}")
	public List<Appointments> getByUserEmail(@PathVariable String email) {
		return appointmentService.getByUserEmail(email);
	}
	@GetMapping("/getByDoctorEmail/{email}")
	public List<Appointments> getByDoctorEmail(@PathVariable String email) {
		return appointmentService.getByDoctorEmail(email);
	}
	@PostMapping("/addAppointment")
	public String addAppointment(@RequestBody Appointments	appointment) {
		return appointmentService.addAppointment(appointment);
	}
	
}

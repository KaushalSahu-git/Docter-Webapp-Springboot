package com.incapp.doctor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.User;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	String URL="http://localhost:9900";
	RestTemplate restTemplate=new RestTemplate();
	
	@PostMapping("/addAppointment")
	public String addAppointment(@ModelAttribute Appointments	appointment,HttpSession session,ModelMap model) {
		appointment.setUser_email(((User)session.getAttribute("user")).getEmail());
		appointment.setBooking_date_time(new java.sql.Date(new Date().getTime()));
		String API="/appointment/addAppointment";
		String result=restTemplate.postForObject(URL+API, appointment, String.class);
		model.addAttribute("msg",result);

		API="/appointment/getByUserEmail/"+((User)session.getAttribute("user")).getEmail();
		List<Appointments> appointments=restTemplate.getForObject(URL+API,List.class);
		model.addAttribute("apts",appointments);
		
		return "MyAppointments";
	}
	@GetMapping("/dffhdf")
	public String searchDoctor(@RequestParam String state,@RequestParam String city,ModelMap model) {
		String API="/doctor/getByStateCity/"+state+"/"+city;
		List<Doctor> doctors=restTemplate.getForObject(URL+API,List.class);
		model.addAttribute("doctors",doctors);
		return "FindDoctor";
	} 
}

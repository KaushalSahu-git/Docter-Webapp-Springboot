package com.incapp.doctor.controller;

import java.util.List;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.User;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
	
	String URL="http://localhost:9900";
	RestTemplate restTemplate=new RestTemplate();
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user,HttpSession session) {
		String API="/user/save";
		//Password Encoding
		//BCryptPasswordEncoder b=new BCryptPasswordEncoder();
		//user.setPassword(b.encode(user.getPassword()));
		//end
		
		User u=restTemplate.postForObject(URL+API,user, User.class);
		session.setAttribute("user", u);
		return "UserHome";
	}
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User user,HttpSession session,ModelMap model) {
		String API="/user/updateUser";
		restTemplate.put(URL+API, user, User.class);
		model.addAttribute("msg","Success!");
		user=restTemplate.getForObject(URL+"/user/getByEmail/"+user.getEmail(),User.class);
		session.setAttribute("user", user);
		return "UserProfile";
	}
	@GetMapping("/FindDoctor")
	public String findDoctor() {
		return "FindDoctor";
	}
	@GetMapping("/MyAppointments")
	public String myAppointments(HttpSession session,ModelMap model) {
		String API="/appointment/getByUserEmail/"+((User)session.getAttribute("user")).getEmail();
		List<Appointments> appointments=restTemplate.getForObject(URL+API,List.class);
		model.addAttribute("apts",appointments);
		return "MyAppointments";
	}
	@GetMapping("/Help")
	public String help() {
		return "Help";
	}
	@GetMapping("/UserProfile")
	public String userProfile() {
		return "UserProfile";
	}
	@GetMapping("/UserHome")
	public String userHome() {
		return "UserHome";
	}
	@PostMapping("/videocall")
	public String videocall(@RequestParam String speciality) {
		
		return "videocall";
	}
}

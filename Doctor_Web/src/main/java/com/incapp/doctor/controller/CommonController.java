package com.incapp.doctor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class CommonController {
	
	String URL="http://localhost:9900";
	RestTemplate restTemplate=new RestTemplate();
	
	@GetMapping(value = {"/","/index"})
	public String home() {
		return "index";
	}
	@GetMapping("/login-signup")
	public String loginSignup() {
		return "login-signup";
	}
	@GetMapping("/update-password")
    public String showUpdatePasswordPage() {
        return "update-password";
    }
	@PostMapping("/login")
	public String login(@RequestParam String email,@RequestParam String password,HttpSession session, ModelMap model) {
		
		String API="/user/getByEmail/"+email;
		User u=restTemplate.getForObject(URL+API, User.class);
		if(u!=null) {
			API="/user/login/"+email+"/"+password;
			u=restTemplate.getForObject(URL+API, User.class);
			if(u!=null) {
				session.setAttribute("user", u);
				return "UserHome";
			}else {
				model.addAttribute("msg","Invalid Entries!");
				return "login-signup";
			}
		}else {
			API="/doctor/getByEmail/"+email;
			Doctor d=restTemplate.getForObject(URL+API, Doctor.class);
			if(d!=null) {
				API="/doctor/login/"+email+"/"+password;
				d=restTemplate.getForObject(URL+API, Doctor.class);
				if(d!=null) {
					session.setAttribute("doctor", d);
					List<DoctorNotAvail> dna=restTemplate.getForObject(URL+"/doctor/getDocNotAvail/"+email,List.class);
					session.setAttribute("dna", dna);
					return "DoctorHome";
				}else {
					model.addAttribute("msg","Invalid Entries!");
					return "login-signup";
				}
			}else {
				model.addAttribute("msg","Invalid Entries!");
				return "login-signup";
			}
		} 
	}
	@GetMapping("/Logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "login-signup";
	}
	@GetMapping("/SearchDoctor")
	public String searchDoctor(@RequestParam String state,@RequestParam String city,ModelMap model) {
		String API="/doctor/getByStateCity/"+state+"/"+city;
		List<Doctor> doctors=restTemplate.getForObject(URL+API,List.class);
		model.addAttribute("doctors",doctors);
		return "FindDoctor";
	}
	@GetMapping("/SearchDoctorSpa")
	public String SearchDoctorSpa(@RequestParam String speciality,ModelMap model){
		String API="/doctor/getBySpeciality/"+speciality;
		List<Doctor> doctors=restTemplate.getForObject(URL+API, List.class);
		model.addAttribute("doctors",doctors);
		return "FindDoctor";
	}
}

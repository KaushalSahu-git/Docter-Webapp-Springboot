package com.incapp.doctor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	String URL="http://localhost:9900";
	RestTemplate restTemplate=new RestTemplate();
	
	@GetMapping("/DoctorHome")
	public String home() {
		return "DoctorHome";
	}
	@GetMapping("/DoctorOnline")
	public String DoctorOnline() {
		return "DoctorOnline";
	}
	@GetMapping("/DoctorAppointments")
	public String doctorAppointments(HttpSession session,ModelMap model) {
	    String API="/appointment/getByDoctorEmail/"+((Doctor)session.getAttribute("doctor")).getEmail();
		List<Appointments> appointments=restTemplate.getForObject(URL+API,List.class);
		model.addAttribute("apts",appointments);
		return "DoctorAppointments";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute Doctor doctor,HttpSession session) {
		String API="/doctor/save";
		//Password Encoding
		//BCryptPasswordEncoder b=new BCryptPasswordEncoder();
		//user.setPassword(b.encode(user.getPassword()));
		//end
		
		Doctor d=restTemplate.postForObject(URL+API,doctor, Doctor.class);
		System.out.println(d);
		session.setAttribute("doctor", d);
		
		List<DoctorNotAvail> dna=restTemplate.getForObject(URL+"/doctor/getDocNotAvail/"+doctor.getEmail(),List.class);
		session.setAttribute("dna", dna);
		
		return "DoctorHome";
	}
	@PostMapping("/updateDoctor")
	public String updateDoctor(@ModelAttribute Doctor doctor,HttpSession session,ModelMap model) {
		String API="/doctor/updateDoctor";
		restTemplate.put(URL+API, doctor, Doctor.class);
		model.addAttribute("msg","Success!");
		doctor=restTemplate.getForObject(URL+"/doctor/getByEmail/"+doctor.getEmail(),Doctor.class);
		session.setAttribute("doctor", doctor);
		return "DoctorHome";
	}
	@PostMapping("/updateDocAvail") 
	public String updateDocAvail(@ModelAttribute DoctorAvail doctorAvail,HttpSession session,ModelMap model) {
		String email=((Doctor)session.getAttribute("doctor")).getEmail();
		String API="/doctor/updateDocAvail/"+email;
		restTemplate.put(URL+API, doctorAvail, DoctorAvail.class);
		Doctor doctor=restTemplate.getForObject(URL+"/doctor/getByEmail/"+email,Doctor.class);
		session.setAttribute("doctor", doctor);
		model.addAttribute("msg","Success!");
		return "DoctorHome";
	}
	@PostMapping("/addDocNotAvail")
	public String addDocNotAvail(@ModelAttribute DoctorNotAvail doctorNotAvail ,HttpSession session,ModelMap model) {
		String email=((Doctor)session.getAttribute("doctor")).getEmail();
		doctorNotAvail.setEmail(email);
		String API="/doctor/addDocNotAvail";
		boolean result=restTemplate.postForObject(URL+API, doctorNotAvail, Boolean.class);
		if(result) {
			List<DoctorNotAvail> dna=restTemplate.getForObject(URL+"/doctor/getDocNotAvail/"+email,List.class);
			session.setAttribute("dna", dna);
			model.addAttribute("msg","Success!");
		}else {
			model.addAttribute("msg","Already Exist!");
		}
		return "DoctorHome";
	}
	@PostMapping("/DoctorDetails")
	public String doctorDetails(@RequestParam String doctorEmail, ModelMap model) {
		System.err.println(doctorEmail);
		String API="/doctor/getByEmail/"+doctorEmail;
		Doctor doctor=restTemplate.getForObject(URL+API,Doctor.class);
		System.err.println(doctor);
		model.addAttribute("doctor",doctor);
		return "DoctorDetails"; 
	}
	@PostMapping("/reset-password")
	public String restPassword(@RequestParam String email) {
		String API = "/doctor/reset-password?email=" + email;
		ResponseEntity<String> response = restTemplate.postForEntity(URL+API,null, String.class);
		return "DoctorHome";
	}
	@PostMapping("/update-password")
    public String updatePassword(@RequestParam String token, @RequestParam String newPassword) {

		System.out.println("Received token: " + token);
        System.out.println("Received newPassword: " + newPassword);

		String API = "/doctor/update-password?token=" + token + "&newPassword=" + newPassword;
		ResponseEntity<String> response = restTemplate.postForEntity(URL + API, null, String.class);
	  return "login-signup";
    }
}

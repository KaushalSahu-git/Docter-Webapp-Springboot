package com.incapp.doctors.controller;

import org.springframework.web.bind.annotation.RestController;

import com.incapp.doctors.mail.EmailService;
import com.incapp.doctors.model.Appointments;
import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.DoctorNotAvail;
import com.incapp.doctors.model.User;
import com.incapp.doctors.service.DoctorService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.incapp.doctors.utils.JwtUtil;



@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/save") 
	public Doctor save(@RequestBody Doctor	doctor) {
		doctorService.save(doctor);
		return doctor;
	}
	@PutMapping("/updateDocAvail/{email}") 
	public boolean updateDocAvail(@PathVariable String email,@RequestBody DoctorAvail	doctorAvail) {
		doctorService.updateDocAvail(email, doctorAvail);
		return true;
	}
	@PutMapping("/updateDoctor") 
	public void updateDoctor(@RequestBody Doctor doctor) {
		doctorService.updateDoctor(doctor);
	}
	
	@GetMapping("/get")
	public List<Doctor> get() {
		return doctorService.get();
	}
	@GetMapping("/getByEmail/{email}")
	public Doctor getByEmail(@PathVariable String email) {
		return doctorService.getByEmail(email);
	}
	@GetMapping("/getByName/{name}")
	public List<Doctor> getByName(@PathVariable String name) {
		return doctorService.get(name);
	}

	@GetMapping("/getBySpeciality/{speciality}")
	public List<Doctor> getBySpeciality(@PathVariable String speciality) {
		return doctorService.getBySpeciality(speciality);
	}

	@GetMapping("/getByStateCity/{state}/{city}")
	public List<Doctor> getByStateCity(@PathVariable String state,@PathVariable String city) {
		return doctorService.get(state,city);
	}
	
	@PostMapping("/addDocNotAvail")
	public boolean addDocNotAvail(@RequestBody DoctorNotAvail	doctorNotAvail) {
		return doctorService.addDocNotAvail(doctorNotAvail);
	}
	@GetMapping("/getDocNotAvail/{email}")
	public List<DoctorNotAvail> getDocNotAvail(@PathVariable String email) {
		return doctorService.getDocNotAvail(email);
	}
	@GetMapping("/login/{email}/{password}")
	public Doctor login(@PathVariable String email,@PathVariable String password) {
		return doctorService.login(email,password);
	}
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam String email) {
		Doctor doctor = doctorService.getByEmail(email);
		if(doctor == null){
			return ResponseEntity.status(404).body("Email not foud");
		}
		
		String token = jwtUtil.generateToken(email);
		String resetLink = "http://localhost:9901/update-password?token="+token;

		System.out.println("Passowrd reset link:" + resetLink);
		
		emailService.sendResetLink(email, resetLink);

		return ResponseEntity.ok("Password reset link sent to email.");
	}
	
	@PostMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestParam String token, @RequestParam String newPassword){
		System.out.println(token);
		if(!jwtUtil.isTokenValid(token)){
			return ResponseEntity.badRequest().body("Invalid or Expired token.");
		}

		String email = jwtUtil.extractEmail(token);
		Doctor doctor = doctorService.getByEmail(email);
		if(doctor == null){
			return ResponseEntity.status(404).body("User not Found.");
		}
		doctor.setPassword(newPassword);
		doctorService.save(doctor);

		return ResponseEntity.ok("Password update Successfully.");
	}
}

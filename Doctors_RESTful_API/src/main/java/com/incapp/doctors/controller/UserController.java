package com.incapp.doctors.controller;

import org.springframework.web.bind.annotation.RestController;

import com.incapp.doctors.model.DoctorAvail;
import com.incapp.doctors.model.User;
import com.incapp.doctors.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save") 
	public User save(@RequestBody User	user) {
		userService.save(user);
		return user;
	}
	@GetMapping("/login/{email}/{password}")
	public User login(@PathVariable String email,@PathVariable String password) {
		return userService.login(email,password);
	}
	@GetMapping("/get")
	public List<User> get() {
		return userService.get();
	}
	@GetMapping("/getByEmail/{email}")
	public User getByEmail(@PathVariable String email) {
		return userService.getByEmail(email);
	}
	@PutMapping("/updateUser") 
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
}

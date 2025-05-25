package com.incapp.doctors.service;

import java.util.List;

import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.User;

public interface UserService {
	public void save(User user) ;
	public List<User> get() ;
	public User getByEmail(String email) ;
	public User login(String email,String password) ;
	public void updateUser(User user) ;
}

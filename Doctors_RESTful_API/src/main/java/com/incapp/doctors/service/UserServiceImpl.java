package com.incapp.doctors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.doctors.dao.UserDAO;
import com.incapp.doctors.model.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Transactional
	@Override
	public void save(User user) {
		userDAO.save(user);
	}
	
	@Override
	public User login(String email, String password) {
		return userDAO.login(email, password);
	}
	
	@Override
	public List<User> get() {
		return userDAO.get();
	}

	@Override
	public User getByEmail(String email) {
		return userDAO.getByEmail(email);
	}
	@Transactional
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

}

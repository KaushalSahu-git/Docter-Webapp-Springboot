package com.incapp.doctors.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incapp.doctors.model.Doctor;
import com.incapp.doctors.model.User;

import jakarta.persistence.EntityManager;

@Repository
public class UserDAO {
	@Autowired
	private EntityManager entityManager;
	
	public void save(User user) {
		//returns current session
		Session session= entityManager.unwrap(Session.class);
		session.persist(user);
	}
	
	public List<User> get() {
		Session session= entityManager.unwrap(Session.class);
		List<User> list=session.createQuery("from User",User.class).list();
		return list;
	}
	
	public User getByEmail(String email) {
		Session session= entityManager.unwrap(Session.class);
		User user=session.get(User.class, email);
		return user;
	}
	public User login(String email,String password) {
		Session session= entityManager.unwrap(Session.class);
		List<User> list= session.createQuery("from User u where u.email= :email and u.password= :password ",User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.list();
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
	public void updateUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		User d = session.get(User.class, user.getEmail());
		d.setPhone(user.getPhone());
		d.setDob(user.getDob());
		d.setGender(user.getGender());
		session.persist(d);
	}
}

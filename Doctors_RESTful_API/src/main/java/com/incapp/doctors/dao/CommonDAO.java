package com.incapp.doctors.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incapp.doctors.model.User;

import jakarta.persistence.EntityManager;

@Repository
public class CommonDAO {
	@Autowired
	private EntityManager entityManager;
	
	
}

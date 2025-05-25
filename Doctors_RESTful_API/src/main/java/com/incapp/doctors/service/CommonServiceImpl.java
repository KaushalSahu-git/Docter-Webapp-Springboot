package com.incapp.doctors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.doctors.dao.CommonDAO;
import com.incapp.doctors.dao.UserDAO;
import com.incapp.doctors.model.User;

import jakarta.transaction.Transactional;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonDAO commonDAO;
	
	

}

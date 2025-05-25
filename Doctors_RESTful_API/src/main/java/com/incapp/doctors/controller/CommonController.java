package com.incapp.doctors.controller;

import org.springframework.web.bind.annotation.RestController;

import com.incapp.doctors.model.User;
import com.incapp.doctors.service.CommonService;
import com.incapp.doctors.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
}

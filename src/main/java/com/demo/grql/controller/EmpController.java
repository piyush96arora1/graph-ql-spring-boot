package com.demo.grql.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.grql.service.EmployeeService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("")
public class EmpController {
	@Autowired
	EmployeeService service;
	@PostMapping("/getemp")
	public ResponseEntity<Object> getEmployeeConfigs(@RequestBody String empReqst)
	{	//System.out.println(service.getAllEmployees().get(0).getAddress().getCountry());
		 ExecutionResult execute= service.getGraphQL().execute(empReqst);
		 return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
}

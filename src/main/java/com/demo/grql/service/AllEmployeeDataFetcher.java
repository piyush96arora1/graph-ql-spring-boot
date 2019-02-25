package com.demo.grql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.grql.model.Employee;
import com.demo.grql.repo.EmployeeRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
@Component
public class AllEmployeeDataFetcher implements DataFetcher<List<Employee>>{
	@Autowired
	EmployeeRepo repo;

	@Override
	public List<Employee> get(DataFetchingEnvironment environment) {
		return repo.findAll();
	}
}

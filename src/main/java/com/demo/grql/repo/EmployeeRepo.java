package com.demo.grql.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.grql.model.Employee;
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, String> {

	public List<Employee> findAll();
	
}

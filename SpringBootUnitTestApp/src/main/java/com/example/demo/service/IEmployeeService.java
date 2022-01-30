package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface IEmployeeService {
	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployee(Integer id);

	public void deleteEmployee(Integer id);
	
	Employee updateEmployee(int id,double salary);

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService service;

	@PostMapping("/save")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@GetMapping("/all")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Employee> getAllEmployees() {
		return service.getAllEmployees();
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee getEmployee(@PathVariable Integer id) {
		return service.getEmployee(id);
	}

	@DeleteMapping("delete/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Integer id) {
		service.deleteEmployee(id);

	}

	@PutMapping("/update/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		Employee emp = service.getEmployee(id);
		emp.setEmpName(employee.getEmpName());
		emp.setEmpSalary(employee.getEmpSalary());
		service.saveEmployee(emp);
		return emp;

	}

}

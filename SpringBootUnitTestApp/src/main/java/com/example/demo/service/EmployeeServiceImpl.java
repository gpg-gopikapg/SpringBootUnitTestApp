package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public Employee getEmployee(Integer id) {
		return repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found with Id:" + id));
		/*Optional<Employee> employee = repo.findById(id);
		if (employee.isPresent())
			return employee.get();
		throw new EmployeeNotFoundException("Employee Not Found with Id:" + id);*/
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee employee=getEmployee(id);
		repo.delete(employee);
	}

	@Override
	public Employee updateEmployee(int id, double salary) {
		Employee employee=getEmployee(id);
		employee.setEmpSalary(salary);
		return repo.save(employee);
	}
}
	
package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmployeeService;
import com.example.demo.dto.EmployeeLookupResponse;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.model.Employee;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	//Create Employee

	@PostMapping
	public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody Employee employee) {

		EmployeeResponse emp = employeeService.createEmployee(employee);

		return new ResponseEntity<EmployeeResponse>(emp, HttpStatus.CREATED);

	}

	// Update Employee

	@PutMapping("{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable("id") Integer empId,
			@Valid @RequestBody Employee employee) {

		employee.setEmpId(empId);
		EmployeeResponse updatedEmployee = employeeService.updateEmployee(employee);

		return new ResponseEntity<EmployeeResponse>(updatedEmployee, HttpStatus.OK);

	}
	
	//Update Employee's Department

	@PutMapping("/{empId}/department/{dptId}")
	public ResponseEntity<String> updateEmployeesDepartment(@PathVariable("empId") Integer empId,
			@PathVariable("dptId") Integer dptId) {

		employeeService.updateEmployeeDepartment(empId, dptId);

		return new ResponseEntity<String>("Employee department updated successfully", HttpStatus.OK);

	}
	
	//Fetch all Employees

	@GetMapping
	public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(@RequestParam int pageNo,
			@RequestParam(defaultValue = "20") int pageSize) {

		Page<EmployeeResponse> empList = employeeService.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<Page<EmployeeResponse>>(empList, HttpStatus.OK);

	}
	
	//List Employee Name and ID

	@GetMapping("/lookup")
	public ResponseEntity<Page<?>> getAllEmployeesNameAndId(@RequestParam int pageNo,
			@RequestParam(defaultValue = "20") int pageSize, @RequestParam boolean lookup) {

		if (lookup) {

			Page<EmployeeLookupResponse> empList = employeeService.getAllEmployeesNameAndId(pageNo, pageSize);
			return new ResponseEntity<Page<?>>(empList, HttpStatus.OK);
		}
		Page<EmployeeResponse> empList = employeeService.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<Page<?>>(empList, HttpStatus.OK);

	}

}

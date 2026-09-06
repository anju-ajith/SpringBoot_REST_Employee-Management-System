package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Service.DepartmentService;
import com.example.demo.dto.DepartmentExpandResponse;
import com.example.demo.dto.DepartmentResponse;
import com.example.demo.model.Department;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//Add Department

	@PostMapping
	public ResponseEntity<DepartmentResponse> addDepartment(@Valid @RequestBody Department department) {
		department.setCreationDate(LocalDate.now());
		DepartmentResponse departments = departmentService.addDepartment(department);
		return new ResponseEntity<DepartmentResponse>(departments, HttpStatus.CREATED);
	}
	
	//Update Department

	@PutMapping("{id}")
	public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable("id") Integer dptId,
		@Valid	@RequestBody Department department) {
		department.setId(dptId);

		DepartmentResponse updatedDepartment = departmentService.updateDepartment(department);

		return new ResponseEntity<DepartmentResponse>(updatedDepartment, HttpStatus.OK);

	}
	
	//Delete Department

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Integer dptId) {

		departmentService.deleteDepartmentById(dptId);
		return new ResponseEntity<String>("Department deleted successfully", HttpStatus.OK);

	}
	
	//Fetch all Departments

	@GetMapping
	public ResponseEntity<Page<DepartmentResponse>> getAllDepartments(@RequestParam int pageNo,
			@RequestParam(defaultValue = "20") int pageSize) {

		Page<DepartmentResponse> dptList = departmentService.getAllDepartment(pageNo, pageSize);
		return new ResponseEntity<Page<DepartmentResponse>>(dptList, HttpStatus.OK);

	}
	
	//Expand Employees under Departments

	@GetMapping("{dptId}")
	public ResponseEntity<?> getEmployeesUnderDepartment(@PathVariable("dptId") Integer dptId,
			@RequestParam String expand) {

		if ("employee".equalsIgnoreCase(expand)) {

			DepartmentExpandResponse departments = departmentService.getEmployeesUnderDepartment(dptId);
			
			 return new ResponseEntity<>(departments,HttpStatus.OK); 
		}
		
		DepartmentResponse  department = departmentService.getDepartment(dptId);
		
		return new ResponseEntity<>(department,HttpStatus.OK);
		

	}

}

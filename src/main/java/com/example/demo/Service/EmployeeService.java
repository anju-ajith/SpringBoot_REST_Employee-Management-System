package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.EmployeeLookupResponse;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.model.Employee;

public interface EmployeeService {
	EmployeeResponse createEmployee (Employee employe);
	EmployeeResponse updateEmployee(Employee employee);
	void updateEmployeeDepartment(int empId,int dptId);
	Page<EmployeeResponse> getAllEmployees(int pageNo,int pageSize);
	Page<EmployeeLookupResponse> getAllEmployeesNameAndId(int pageNo,int pageSize);
	        

}

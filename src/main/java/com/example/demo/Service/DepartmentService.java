package com.example.demo.Service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.DepartmentExpandResponse;
import com.example.demo.dto.DepartmentResponse;
import com.example.demo.model.Department;

public interface DepartmentService {
	DepartmentResponse addDepartment(Department department);
	DepartmentResponse updateDepartment(Department department);
	void deleteDepartmentById(int dptId);
	  Page<DepartmentResponse>  getAllDepartment(int pageNo,int pageSize);
	  DepartmentExpandResponse getEmployeesUnderDepartment(int dptId);
	  DepartmentResponse getDepartment(int dptId);
	  
	

}

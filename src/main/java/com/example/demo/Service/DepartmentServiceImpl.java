package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentExpandResponse;
import com.example.demo.dto.DepartmentResponse;
import com.example.demo.dto.EmployeeLookupResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepository depRepo;
	private final EmployeeRepository empRepo;

	public DepartmentServiceImpl(DepartmentRepository depRepo, EmployeeRepository empRepo) {
		this.depRepo = depRepo;
		this.empRepo = empRepo;
	}

	@Override
	public DepartmentResponse addDepartment(Department department) {
		// TODO Auto-generated method stub

		if (department.getDepartmentHead() != null) {
			int empId = department.getDepartmentHead().getEmpId();
			Employee employee = empRepo.findById(empId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + empId));

			department.setDepartmentHead(employee);
		}

		Department departments = depRepo.save(department);

		DepartmentResponse response = new DepartmentResponse();

		response.setId(departments.getId());
		response.setName(departments.getName());
		response.setCreationDate(departments.getCreationDate());

		if (departments.getDepartmentHead() != null) {
			response.setDepartmentHead(departments.getDepartmentHead().getName());
		}

		return response;

	}

	@Override
	public DepartmentResponse updateDepartment(Department department) {
		
		Department existingDepartment = depRepo.findById(department.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Department not found with id: " + department.getId()));
		

		existingDepartment.setName(department.getName());
		existingDepartment.setCreationDate(department.getCreationDate());
		
		 if (department.getDepartmentHead() != null) {

		        int empId = department.getDepartmentHead().getEmpId();

		        Employee employee = empRepo.findById(empId)
		                .orElseThrow(() ->
		                        new ResourceNotFoundException(
		                                "Employee not found with id: " + empId
		                        )
		                );
		        existingDepartment.setDepartmentHead(employee);
		 }
		 
		
		Department updatedDepartment = depRepo.save(existingDepartment);

		DepartmentResponse response = new DepartmentResponse();

		response.setId(updatedDepartment.getId());
		response.setName(updatedDepartment.getName());
		response.setCreationDate(updatedDepartment.getCreationDate());

		if (updatedDepartment.getDepartmentHead() != null) {
			response.setDepartmentHead(updatedDepartment.getDepartmentHead().getName());
		}

		return response;

	}

	@Override
	public void deleteDepartmentById(int dptId) {
		// TODO Auto-generated method stub
		boolean employeesExist = empRepo.existsByDepartment_Id(dptId);
		if (employeesExist) {
			throw new RuntimeException("Cannot delete department. Employees are assigned to this department.");
		}

		depRepo.deleteById(dptId);

	}

	@Override
	public Page<DepartmentResponse> getAllDepartment(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Department> departmentPage = depRepo.findAll(pageable);

		Page<DepartmentResponse> responsePage = departmentPage.map(department -> {
			DepartmentResponse response = new DepartmentResponse();

			response.setId(department.getId());
			response.setName(department.getName());
			response.setCreationDate(department.getCreationDate());

			if (department.getDepartmentHead() != null) {
				response.setDepartmentHead(department.getDepartmentHead().getName());
			}
			return response;

		});

		return responsePage;
	}

	@Override
	public DepartmentExpandResponse getEmployeesUnderDepartment(int dptId) {
		// TODO Auto-generated method stub
		Department department = depRepo.findById(dptId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dptId));

		List<Employee> employees = empRepo.findByDepartment_Id(dptId);

		DepartmentExpandResponse response = new DepartmentExpandResponse();
		response.setId(department.getId());
		response.setName(department.getName());
		response.setCreationDate(department.getCreationDate());

		if (department.getDepartmentHead() != null) {
			response.setDepartmentHead(department.getDepartmentHead().getName());
		}

		List<EmployeeLookupResponse> employeeList = new ArrayList<>();

		for (Employee employee : employees) {

			EmployeeLookupResponse emp = new EmployeeLookupResponse(employee.getEmpId(), employee.getName());

			employeeList.add(emp);
		}

		response.setEmployees(employeeList);

		return response;

	}

	@Override
	public DepartmentResponse getDepartment(int dptId) {
		// TODO Auto-generated method stub
		Department department = depRepo.findById(dptId).get();
		DepartmentResponse response = new DepartmentResponse();
		response.setName(department.getName());
		response.setCreationDate(department.getCreationDate());
		response.setId(department.getId());
		response.setDepartmentHead(department.getDepartmentHead().getName());
		return response;

	}

}

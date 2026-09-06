package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeLookupResponse;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository empRepo;
	private final DepartmentRepository deptRepo;

	public EmployeeServiceImpl(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
		this.empRepo = empRepo;
		this.deptRepo = deptRepo;
	}

	@Override
	public EmployeeResponse createEmployee(Employee employe) {
		// TODO Auto-generated method stub
		Employee emp = empRepo.save(employe);

		EmployeeResponse response = new EmployeeResponse();

		response.setName(emp.getName());
		response.setAddress(emp.getAddress());
		response.setDob(emp.getDob());
		response.setEmpId(emp.getEmpId());
		response.setJoiningDate(emp.getJoiningDate());
		response.setRole(emp.getRole());
		response.setSalary(emp.getSalary());
		response.setYearlyBonusPercentage(emp.getYearlyBonusPercentage());

		if (emp.getDepartment() != null) {

			Department department = deptRepo.findById(emp.getDepartment().getId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Department not found with id: " + emp.getDepartment().getId()));
			response.setDepartment(department.getName());
		}

		if (emp.getReportingManager() != null) {
			Employee manager = empRepo.findById(emp.getReportingManager().getEmpId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Employee not found with id: " + emp.getReportingManager().getEmpId()));
			response.setReportingManager(manager.getName());
		}

		return response;

	}

	@Override
	public EmployeeResponse updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

		Employee existingEmployee = empRepo.findById(employee.getEmpId()).get();
		existingEmployee.setName(employee.getName());
		existingEmployee.setAddress(employee.getAddress());
		existingEmployee.setDob(employee.getDob());
		existingEmployee.setJoiningDate(employee.getJoiningDate());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setRole(employee.getRole());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());
		existingEmployee.setReportingManager(employee.getReportingManager());
		
		Employee updatedEmployee = empRepo.save(existingEmployee);
		
		EmployeeResponse response = new EmployeeResponse();
		response.setName(updatedEmployee.getName());
		response.setAddress(updatedEmployee.getAddress());
		response.setDob(updatedEmployee.getDob());
		response.setEmpId(updatedEmployee.getEmpId());
		response.setJoiningDate(updatedEmployee.getJoiningDate());
		response.setRole(updatedEmployee.getRole());
		response.setSalary(updatedEmployee.getSalary());
		response.setYearlyBonusPercentage(updatedEmployee.getYearlyBonusPercentage());
		if (updatedEmployee.getDepartment() != null) {

			Department department = deptRepo.findById(updatedEmployee.getDepartment().getId()).get();
					
			response.setDepartment(department.getName());
		}

		if (updatedEmployee.getReportingManager() != null) {
			Employee manager = empRepo.findById(updatedEmployee.getReportingManager().getEmpId()).get();
					
			response.setReportingManager(manager.getName());
		}

		return response;

		
	}

	@Override
	public void updateEmployeeDepartment(int empId, int dptId) {
		// TODO Auto-generated method stub
		Employee employee = empRepo.findById(empId).orElseThrow(

				() -> new ResourceNotFoundException("Employee not found with id: " + empId));
		Department department = deptRepo.findById(dptId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dptId));

		employee.setDepartment(department);

		empRepo.save(employee);

	}
	
	

	@Override
	public Page<EmployeeResponse> getAllEmployees(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<Employee> employeePage = empRepo.findAll(pageable);

		Page<EmployeeResponse> responsePage = employeePage.map(employee -> {

			EmployeeResponse response = new EmployeeResponse();

			response.setEmpId(employee.getEmpId());
			response.setName(employee.getName());
			response.setDob(employee.getDob());
			response.setSalary(employee.getSalary());
			response.setAddress(employee.getAddress());
			response.setRole(employee.getRole());
			response.setJoiningDate(employee.getJoiningDate());
			response.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());

			if (employee.getDepartment() != null) {
				response.setDepartment(employee.getDepartment().getName());
			}

			if (employee.getReportingManager() != null) {
				response.setReportingManager(employee.getReportingManager().getName());
			}

			return response;
		});

		return responsePage;

	}

	@Override
	public Page<EmployeeLookupResponse> getAllEmployeesNameAndId(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> employeePage = empRepo.findAll(pageable);

		Page<EmployeeLookupResponse> responsePage = employeePage.map(employee -> {
			EmployeeLookupResponse response = new EmployeeLookupResponse();
			response.setEmpId(employee.getEmpId());
			response.setName(employee.getName());
			return response;
		});
		return responsePage;
	}

}

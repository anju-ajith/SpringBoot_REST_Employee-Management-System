package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	@NotBlank(message = "Name is required")
	private String name;
	@NotNull(message = "Date of birth is required")
	private LocalDate dob;
	@Positive(message = "Salary must be greater than 0")
	private double salary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	@NotNull(message = "Department is required")
	private Department department;
	
	@NotBlank(message = "Address is required")
	private String address;
	@NotBlank(message = "Role is required")
	private String role;
	@NotNull(message = "Joining date is required")
	private LocalDate joiningDate;
	@PositiveOrZero(message = "Bonus percentage cannot be negative")
	private double yearlyBonusPercentage;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporting_manager_id")
	private Employee reportingManager;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public double getYearlyBonusPercentage() {
		return yearlyBonusPercentage;
	}

	public void setYearlyBonusPercentage(double yearlyBonusPercentage) {
		this.yearlyBonusPercentage = yearlyBonusPercentage;
	}

	public Employee getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Employee reportingManager) {
		this.reportingManager = reportingManager;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", dob=" + dob + ", salary=" + salary + ", address="
				+ address + ", role=" + role + ", joiningDate=" + joiningDate + ", yearlyBonusPercentage="
				+ yearlyBonusPercentage + "]";
	}

}

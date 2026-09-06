package com.example.demo.dto;

import java.time.LocalDate;

public class EmployeeResponse {
	 private int empId;
	    private String name;
	    private LocalDate dob;
	    private double salary;
	    private String department;
	    private String address;
	    private String role;
	    private LocalDate joiningDate;
	    private double yearlyBonusPercentage;
	    private String reportingManager;
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
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
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
		public String getReportingManager() {
			return reportingManager;
		}
		public void setReportingManager(String reportingManager) {
			this.reportingManager = reportingManager;
		}
		@Override
		public String toString() {
			return "EmployeeResponse [empId=" + empId + ", name=" + name + ", dob=" + dob + ", salary=" + salary
					+ ", department=" + department + ", address=" + address + ", role=" + role + ", joiningDate="
					+ joiningDate + ", yearlyBonusPercentage=" + yearlyBonusPercentage + ", reportingManager="
					+ reportingManager + "]";
		}

	    
		
		

}

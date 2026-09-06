package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class DepartmentExpandResponse {
	 private int id;
	    private String name;
	    private LocalDate creationDate;
	    private String departmentHead;
	    private List<EmployeeLookupResponse> employees;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDate getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
		}
		public String getDepartmentHead() {
			return departmentHead;
		}
		public void setDepartmentHead(String departmentHead) {
			this.departmentHead = departmentHead;
		}
		public List<EmployeeLookupResponse> getEmployees() {
			return employees;
		}
		public void setEmployees(List<EmployeeLookupResponse> employees) {
			this.employees = employees;
		}


}

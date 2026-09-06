package com.example.demo.dto;

public class EmployeeLookupResponse {
	 private int empId;
	    private String name;
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
		public EmployeeLookupResponse(int empId, String name) {
			super();
			this.empId = empId;
			this.name = name;
		}
		public EmployeeLookupResponse() {
			super();
		}
	    
	    

}

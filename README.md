Employee Management System

A RESTful Employee Management System built using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL

Technologies

Java 17,Spring Boot,Spring Data JPA,MySQL,Maven,Postman,Eclipse

Base URL : http://localhost:8084/api

API Endpoints 

1.Create Employee : POST /api/employees

2.Update Employee : PUT /api/employees/{employeeId}

3.Add Department  : POST /api/department

4.Delete Department : DELETE /api/department/{departmentId}

5.Update Department : PUT /api/department/{departmentId}

6.Update Employee's Department : PUT api/employee/{employeeId}/department/{departmentId}

7.Fetch All Employees : GET  /api/employees?pageNo=0&pageSize=20

8.Fetch All Department : GET /api/department?pageNo=0&pageSize=20 

9.Expand Employees under Departments : GET /api/department/{departmentId}?expand=employee

10.List Employee Name and ID : GET /api/employee/lookup?lookup=true&pageNo=0

JSON Schemas

schemas/

1.employee-request.json 

2.employee-response.json

3.employee-lookup-response.json 

4.department-request.json 

5.department-response.json 

6.department-expand-response.json 

7.error-response.json

DB Scripts

database/schema.sql



















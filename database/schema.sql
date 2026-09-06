CREATE DATABASE IF NOT EXISTS employee_management_db;
USE employee_management_db;
CREATE TABLE department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creation_date DATE,
    department_head_id INT
);
CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE,
    salary DECIMAL(12,2),
    department_id INT NOT NULL,
    address VARCHAR(255),
    role VARCHAR(100),
    joining_date DATE,
    yearly_bonus_percentage DECIMAL(5,2),
    reporting_manager_id INT,

    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id)
        REFERENCES department(id),

    CONSTRAINT fk_employee_manager
        FOREIGN KEY (reporting_manager_id)
        REFERENCES employee(emp_id)
);
ALTER TABLE department
ADD CONSTRAINT fk_department_head
    FOREIGN KEY (department_head_id)
    REFERENCES employee(emp_id);
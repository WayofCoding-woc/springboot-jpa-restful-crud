create database employee_db;
use employee_db;

CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  emp_no varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  is_active bit(1) DEFAULT NULL,
  mobile bigint(20) DEFAULT NULL,
  date_of_joining datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_emp_no (emp_no)
);

CREATE TABLE address (
  id int(11) NOT NULL AUTO_INCREMENT,
  emp_id int(11) DEFAULT NULL,
  line1 varchar(255) DEFAULT NULL,
  line2 varchar(255) DEFAULT NULL,
  pin int(11) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT employee_id_fk FOREIGN KEY(emp_id) REFERENCES employee(id)
);

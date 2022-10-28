package com.leap.training.employee.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> lastName;
	public static volatile SingularAttribute<Employee, ZonedDateTime> hireDate;
	public static volatile SetAttribute<Employee, Department> managedDepartments;
	public static volatile SingularAttribute<Employee, Employee> manager;
	public static volatile SingularAttribute<Employee, Long> employeeId;
	public static volatile SingularAttribute<Employee, Long> salary;
	public static volatile SingularAttribute<Employee, Long> commissionPct;
	public static volatile SingularAttribute<Employee, String> firstName;
	public static volatile SingularAttribute<Employee, String> phoneNumber;
	public static volatile SetAttribute<Employee, Employee> subEmployees;
	public static volatile SingularAttribute<Employee, Job> job;
	public static volatile SingularAttribute<Employee, Department> department;
	public static volatile SingularAttribute<Employee, String> email;

	public static final String LAST_NAME = "lastName";
	public static final String HIRE_DATE = "hireDate";
	public static final String MANAGED_DEPARTMENTS = "managedDepartments";
	public static final String MANAGER = "manager";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String SALARY = "salary";
	public static final String COMMISSION_PCT = "commissionPct";
	public static final String FIRST_NAME = "firstName";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUB_EMPLOYEES = "subEmployees";
	public static final String JOB = "job";
	public static final String DEPARTMENT = "department";
	public static final String EMAIL = "email";

}


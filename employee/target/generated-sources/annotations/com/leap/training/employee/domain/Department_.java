package com.leap.training.employee.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, String> departmentName;
	public static volatile SingularAttribute<Department, Employee> manager;
	public static volatile SetAttribute<Department, JobHistory> jobHistories;
	public static volatile SingularAttribute<Department, Long> departmentId;
	public static volatile SingularAttribute<Department, Location> location;
	public static volatile SetAttribute<Department, Employee> employees;

	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String MANAGER = "manager";
	public static final String JOB_HISTORIES = "jobHistories";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String LOCATION = "location";
	public static final String EMPLOYEES = "employees";

}


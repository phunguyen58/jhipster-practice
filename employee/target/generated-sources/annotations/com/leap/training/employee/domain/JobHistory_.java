package com.leap.training.employee.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JobHistory.class)
public abstract class JobHistory_ {

	public static volatile SingularAttribute<JobHistory, Long> endDate;
	public static volatile SingularAttribute<JobHistory, Long> employeeId;
	public static volatile SingularAttribute<JobHistory, Long> id;
	public static volatile SingularAttribute<JobHistory, Job> job;
	public static volatile SingularAttribute<JobHistory, Department> department;
	public static volatile SingularAttribute<JobHistory, Long> startDate;

	public static final String END_DATE = "endDate";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String ID = "id";
	public static final String JOB = "job";
	public static final String DEPARTMENT = "department";
	public static final String START_DATE = "startDate";

}


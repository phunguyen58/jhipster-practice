package com.leap.training.employee.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Job.class)
public abstract class Job_ {

	public static volatile SingularAttribute<Job, String> jobId;
	public static volatile SingularAttribute<Job, Long> maxSalary;
	public static volatile SetAttribute<Job, JobHistory> jobHistories;
	public static volatile SingularAttribute<Job, String> jobTitle;
	public static volatile SingularAttribute<Job, Long> minSalary;
	public static volatile SetAttribute<Job, Employee> employees;

	public static final String JOB_ID = "jobId";
	public static final String MAX_SALARY = "maxSalary";
	public static final String JOB_HISTORIES = "jobHistories";
	public static final String JOB_TITLE = "jobTitle";
	public static final String MIN_SALARY = "minSalary";
	public static final String EMPLOYEES = "employees";

}


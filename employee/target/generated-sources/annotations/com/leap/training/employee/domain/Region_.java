package com.leap.training.employee.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Region.class)
public abstract class Region_ {

	public static volatile SingularAttribute<Region, Long> regionId;
	public static volatile SingularAttribute<Region, String> regionName;
	public static volatile SetAttribute<Region, Country> countries;

	public static final String REGION_ID = "regionId";
	public static final String REGION_NAME = "regionName";
	public static final String COUNTRIES = "countries";

}


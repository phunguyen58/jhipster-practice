package com.leap.training.employee.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Country.class)
public abstract class Country_ {

	public static volatile SetAttribute<Country, Location> locations;
	public static volatile SingularAttribute<Country, String> countryName;
	public static volatile SingularAttribute<Country, Region> region;
	public static volatile SingularAttribute<Country, String> countryId;

	public static final String LOCATIONS = "locations";
	public static final String COUNTRY_NAME = "countryName";
	public static final String REGION = "region";
	public static final String COUNTRY_ID = "countryId";

}


package com.manning.gwtia.ch13.client.i18nstatic.constants;

import com.google.gwt.i18n.client.Constants;

public interface CountryDetails extends Constants{

	@DefaultFloatValue(59000)
	public float averageSalary();
	   
	@DefaultIntValue(60)
	public int speedLimit();
	   
	@DefaultStringValue("mph")
	public String speedUnits();
}

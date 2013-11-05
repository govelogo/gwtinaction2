package com.manning.gwtia.ch17.linker;

import java.util.SortedSet;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.ConfigurationProperty;
import com.google.gwt.core.ext.linker.PropertyProviderGenerator;
import com.google.gwt.user.rebind.StringSourceWriter;

public class WeekendPropertyProviderGenerator implements PropertyProviderGenerator  {

	@Override
	public String generate(TreeLogger logger, SortedSet<String> possibleValues,
			String fallback, SortedSet<ConfigurationProperty> configProperties)
			throws UnableToCompleteException {
		String queryParam ="date";
		StringSourceWriter body = new StringSourceWriter();
	    body.println("{");
	    body.indent();
	    body.println("var isWeekend = \"workhard\";");
	    body.println("var queryParam = location.search;");
	    body.println("var datum = \"\";");
	    body.println("var qpStart = queryParam.indexOf(\"" + queryParam + "=\");");
	    body.println("if (qpStart >= 0) {");
	    body.indent();
	    body.println("var value = queryParam.substring(qpStart + " + (queryParam.length() + 1) + ");");
	    body.println("var end = queryParam.indexOf(\"&\", qpStart);");
	    body.println("if (end < 0) {");
	    body.indent();
	    body.println("end = queryParam.length;");
	    body.outdent();
	    body.println("}");
	    body.println("datum = queryParam.substring(qpStart + " + (queryParam.length() + 1) + ", end);");
	    body.outdent();
	    body.println("}");
	  body.println("var d = new Date();");
	  body.println("if (isNaN(datum) && datum != null && datum != ''){");
	  body.indent();
	  body.println("var currTokens = datum.split( \"/\" );");
	  body.println("if (currTokens.length > 0) d.setFullYear(currTokens[0],currTokens[1]-1,currTokens[2]);");
	  body.outdent();
	  body.println("}");
	  body.println("isWeekday = !(d.getDay()==0||d.getDay()==6);");
	  body.println("if (!isWeekday) isWeekend = 'partytime';");	
	  String errorParam="forceError";
	  body.println("var qpStart = queryParam.indexOf(\"" + errorParam + "=\");");
	  body.println("if (qpStart >= 0){ " );
	 //body.println("alert('error');");
	 body.println("isWeekend='Who Knows!'}");
	    body.println("return isWeekend;");
	  body.outdent();
		body.println("} ");
		return body.toString();
	}
}




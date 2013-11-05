package com.manning.gwtia.ch11.client.javascriptobject;

import com.google.gwt.core.client.SingleJsoImpl;

/**
 * With GWT 2, JavaScript overlay objects are able to implement interfaces.
 * There are some strict rules, as explained in Ch09 such as including the
 * annotation below to indicate which JavaScript Overlay class implements 
 * this interface
 */
@SingleJsoImpl(value = PersonJavaScriptOverlay.class)
public interface Person {
	public String getName();
	public String getCareer();
	public String getDescription();
	public Person changeCareer(String newCareer);
}

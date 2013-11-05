/**
 * 
 */
package com.manning.gwtia.ch11.client.javascriptobject;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * This is a JavaScript overlay class - you would probably give it a better name in your code,
 * such as PersonImpl, but we make it a mouthful so it is easier to track in the example.
 * 
 * Note that it implements Person interface - the ability for overlays to implement interfaces
 * is new in GWT 2.
 *
 */
public class PersonJavaScriptOverlay extends JavaScriptObject implements Person{
	
	// The required protected constructor of a JavaScript overlay.
	protected PersonJavaScriptOverlay(){}
		
	/**
	 * method to change a career of the person
	 */
	final public native Person changeCareer(String newCareer)/*-{
		this.changeCareer(newCareer);
		return this;
	}-*/;

	/**
	 * Method to get the career by simply returning the underlying element
	 * There is a tacit assumption this element exists.
	 */
	final public native String getCareer()/*-{
		return this.career;
	}-*/;

	/**
	 * Method to build up a string from two underlying elements, overlays allow us to 
	 * encapsulate additional functionality in the Java object.
	 */
	final public String getDescription() {
		return getName() + " the " + getCareer();
	}

	/**
	 * Getting the name by just returning it - again, there is a tacit assumption that
	 * this element exists in the underlying JavaScript object.
	 */
	final public native String getName()/*-{
		return this.name;
	}-*/;
}
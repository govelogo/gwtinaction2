/**
 * 
 */
package com.manning.gwtia.ch11.client.computestyle;

import com.google.gwt.dom.client.Element;

/**
 * The definition of getComputedStyle for IE browsers
 *
 * Note that this will be replaced by MyStyleStandard during compilation for appropriate user agents (browsers) due to
 * rules in ComputeStyleExample.gwt.xml assuming it the instance used is instantiated using GWT.create(MyStyle.class).
 *
 */
public class MyStyle {
	
	/**
	 * A simple JSNI method for use in IE that returns a computed style property for an element
	 * 
	 * @param el The element to return computed style of
	 * @param styleProp The style property for which computed style is required.
	 * @return Value of computed style.
	 * 
	 */
	public native String getComputedStyle(Element el, String styleProp)/*-{
		var cIE = el.currentStyle;
   		return cIE[styleProp];
	}-*/;
}

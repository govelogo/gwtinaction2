/**
 * 
 */
package com.manning.gwtia.ch11.client.computestyle;

import com.google.gwt.dom.client.Element;

/**
 * The definition of getComputedStyle for standards compliant browsers
 * 
 * Note that this must extend MyStyle as we will use the rule in ComputedStyleExample.gwt.xml to
 * replace MyStyle with this class for appropriate user agents (browsers) when MyStyle is instantiated
 * using GWT Deferred Binding, i.e. MyStyle obj = GWT.create(MyStyle.class).
 *
 */
public class MyStyleStandard extends MyStyle{

	/**
	 * A simple JSNI method for use in IE that returns a computed style property for an element.
	 * 
	 * Note that this method overrides the definition in MyStyle.
	 * 
	 * @param el The element to return computed style of
	 * @param styleProp The style property for which computed style is required.
	 * @return Value of computed style.
	 * 
	 */
	public native String getComputedStyle(Element el, String styleProp)/*-{
		// Uncomment next line to trigger any Javascript debugger for the browser
		// debugger;
   		style = "";
   		var c = $doc.defaultView.getComputedStyle(el, null);
   		if (c!=null){
      		style = c.getPropertyValue(styleProp);                 
   		}
   		return style;
	}-*/;
}

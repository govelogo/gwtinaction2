/**
 * 
 */
package com.manning.gwtia.ch11.client.jsonp.jsoverlays;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Simple JavaScript overlay of a JavaScript object that represents 
 * a JSONP object.
 * 
 * That object is an Entry returned from Google's Picassa JSONP API (which will contain
 * zero or more entries).
 * 
 * Note it is not a complete overlay, just coveres those elements we need for our example.
 *
 */
public class Entry extends JavaScriptObject{
	
	// Required protected constructor
	protected Entry(){}
	
	// Get the content JSONP object
	public final native Content getContent()/*-{
		return this.content;
	}-*/; 

	// Get the title of the entry
	// It is a level further down in the JSONP structure hence the "this.title.$t"
	// (the $t is no special notation, it is just what Google have called that field in the 
	//  JSONP stream)
	public final native String getTitle()/*-{
		return this.title.$t;
	}-*/;
}

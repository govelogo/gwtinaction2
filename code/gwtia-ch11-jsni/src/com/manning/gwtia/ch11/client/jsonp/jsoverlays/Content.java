/**
 * 
 */
package com.manning.gwtia.ch11.client.jsonp.jsoverlays;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Simple JavaScript overlay of a JavaScript object that represents 
 * a JSONP object.
 * 
 * That object is the Content of an Entry returned from Google's Picassa JSONP API.
 * 
 * Note it is not a complete overlay, just coveres those elements we need for our example.
 *
 */
public class Content extends JavaScriptObject{
	
	// The Required protected constructor for a JavaScriptOverlay
	protected Content(){}
	
	// Get the src field
	public final native String getSrc()/*-{
		return this.src;
	}-*/;
	
	// Get the number of comments field based on the name in the Google API JSONP object.
	public final native String getNumComments()/*-{
		return this.gphoto$commentCount;
	}-*/;
}

/**
 * 
 */
package com.manning.gwtia.ch11.client.jsonp.jsoverlays;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * Simple JavaScript overlay of a JavaScript object that represents 
 * a JSONP object.
 * 
 * That object is the Feed returned from Google's Picassa JSONP API.
 *
 */
public class Feed extends JavaScriptObject{
	
	// Required protected constructor
	protected Feed(){}
	
	// Returns a JSArray of Entries from the feed by directly accessing the underlying element
	public final native JsArray<Entry> getEntries()/*-{
		  return this.feed.entry;
	}-*/; 

}

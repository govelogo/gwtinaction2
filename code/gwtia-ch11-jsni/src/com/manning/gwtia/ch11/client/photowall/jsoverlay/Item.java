package com.manning.gwtia.ch11.client.photowall.jsoverlay;

import com.google.gwt.core.client.JavaScriptObject;

public class Item extends JavaScriptObject{
	protected Item(){}
	
	public native final String getGUID()/*-{
		return this.guid;
	}-*/; 
	
	public native final String getTitle()/*-{
	return this.title;
}-*/; 
	
	public native final String getDescription()/*-{
	return this.description;
}-*/; 
	
	public native final String getLink()/*-{
	return this.link;
}-*/; 
}

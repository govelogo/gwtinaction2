package com.manning.gwtia.ch17.client.computestyle;

import com.google.gwt.dom.client.Element;

public class MyStyle{
	public native String getComputedStyle(Element el, String styleProp)/*-{
   		style = "";
   		var c = $doc.defaultView.getComputedStyle(el, null);
   		if (c!=null){
      		style = c.getPropertyValue(styleProp);                 
   		}
   		return style;
	}-*/;
	
	public String getVersion(){
		return "Standards version";
	}
}

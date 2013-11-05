package com.manning.gwtia.ch17.client.computestyle;

import com.google.gwt.dom.client.Element;

public class MyStyleIE extends MyStyle {
	public native String getComputedStyle(Element el, String styleProp)/*-{
		var cIE = el.currentStyle;
   		return cIE[styleProp];
	}-*/;
	
	public String getVersion(){
		return "Non Standards Version";
	}
}

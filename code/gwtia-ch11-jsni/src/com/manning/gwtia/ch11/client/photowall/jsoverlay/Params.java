package com.manning.gwtia.ch11.client.photowall.jsoverlay;

import com.google.gwt.core.client.JavaScriptObject;

public class Params extends JavaScriptObject{
	
	protected Params(){}
	
	public static Params newInstance() {
	      return JavaScriptObject.createObject().cast();
	    }

	public final native void setAllowFullScreen(String val)/*-{
		this.allowFullScreen = val;
	}-*/;
	
	public final native void setAllowScriptAccess(String val)/*-{
	this.allowscriptaccess = val;
	}-*/;
}

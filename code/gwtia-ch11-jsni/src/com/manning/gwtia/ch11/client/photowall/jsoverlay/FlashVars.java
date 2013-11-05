package com.manning.gwtia.ch11.client.photowall.jsoverlay;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 * You can increment this to cover the values here: 
 * http://www.cooliris.com/developer/reference/flashvars/
 * 
 * @author tacyad
 *
 */
public class FlashVars extends JavaScriptObject{
	
	public enum INTRO {appear, zoomIn, scrollLeft };
	
	protected FlashVars(){}
	
	public static FlashVars newInstance() {
	      return JavaScriptObject.createObject().cast();
	    }

	public final native void setFeed(String feed)/*-{
		this.feed = feed;
	}-*/;
	
	public final native void setIntro(INTRO intro)/*-{
	    this.intro = intro.toString();
    }-*/;
}

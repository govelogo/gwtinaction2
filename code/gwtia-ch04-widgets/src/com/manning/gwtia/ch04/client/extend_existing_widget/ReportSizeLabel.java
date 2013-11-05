package com.manning.gwtia.ch04.client.extend_existing_widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InlineLabel;

public class ReportSizeLabel extends InlineLabel{

	/**
	 * Simple constructor calling the parent constructor
	 */
	public ReportSizeLabel(String txt){
		super(txt);
	}
	
	/**
	 * Override the parent's addClickHandler to register an exception that click handlers
	 * cannot be added to this widget.
	 */
    public HandlerRegistration addClickHandler(ClickHandler handler){ 
        GWT.log("", new Exception("Cannot add ClickHandler to ReportSizeLabel"));                      
        return null;                                                  
     }
	
    /**
     * Override the parent's onLoad method to display the dimensions.
     * We use the onLoad method as this is called by the GWT widget attach mechanism
     * only once the widget is attached in the DOM (thus it will have dimensions).
     */
	public void onLoad(){
		Window.alert("Dimensions: "+this.getOffsetWidth()+" x " + this.getOffsetHeight());
	}
}

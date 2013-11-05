package com.manning.gwtia.ch13.client.i18ndynamic;

import java.util.MissingResourceException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.i18n.client.Dictionary;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class DynamicExample extends Composite{
	

    final String DEFAULT_LOCALE = "en_GB";
    final String DETAILS = "details";
    final String TITLE = "title";

    
    @UiField Label details;
    @UiField (provided=true) ListBox locale;	
    @UiField Label title;

    
    interface DynamicExampleUiBinder extends UiBinder<Widget, DynamicExample> {}
    private static DynamicExampleUiBinder uiBinder = GWT.create(DynamicExampleUiBinder.class);
    
    public DynamicExample(){
    	locale = new ListBox();
    	initWidget(uiBinder.createAndBindUi(this));
    	this.setSize("100%", "100%");
    	addLocales();
    	setLocale(DEFAULT_LOCALE);
    }
    
    private void addLocales(){
    	locale.addItem("en_US - American English");
    	locale.addItem("en_GB - Brittish English");
    	locale.addItem("sv - Swedish");
    	locale.addItem("ru - Russian");
    	locale.addItem("fr - French");
    	locale.addItem("es - Spanish");
    }
    
    @UiHandler("locale")
    void addTextToScreen (ChangeEvent event){
		String lang = locale.getItemText(locale.getSelectedIndex());
		String locale = lang.split("-")[0].trim();
		setLocale(locale);
    }
    
    private void setLocale(String locale){
    	String theTitle = "";
    	String theDetails  = "";
    	
    	Dictionary dict = Dictionary.getDictionary("labels_"+locale);
    	try{
    		 theTitle = dict.get(TITLE);
    	} catch(MissingResourceException e){
    		theTitle = "Missing Title";
    	} finally {
        	title.setText(theTitle);
    	}
    	try{
        	theDetails = dict.get(DETAILS);
	   	} catch(MissingResourceException e){
	   		theDetails  = "Missing Details";
	   	} finally {
	    	details.setText(theDetails);
	   	}
    }
}

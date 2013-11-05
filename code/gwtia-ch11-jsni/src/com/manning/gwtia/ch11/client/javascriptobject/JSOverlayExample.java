/**
 * 
 */
package com.manning.gwtia.ch11.client.javascriptobject;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * An example of how to use JavaScript and JavaScript Overlay objects
 * 
 * Two sub-sections are displayed on the screen to handle a "person" object.  The top treats person as a JavaScript
 * object; the bottom treats person as a JavaScript overlay.
 * 
 * In both examples you can create a person, and change their career.
 *
 */
public class JSOverlayExample extends Composite {

	// List of potential careers.
	String[] careers = {"doctor","engineer","nurse","teacher","astronaut","person on parental leave","consultant","bus driver","cleaner","unemployed person"};

	//UI Objects we will access.
	@UiField Label career;
	@UiField Label careerOverlay;
	@UiField Label description;	
	@UiField Label descriptionOverlay;
	@UiField DisclosurePanel details;	
	@UiField DisclosurePanel detailsOverlay;
	@UiField Label name;
	@UiField Label nameOverlay;	
	
	@UiField Button createPerson;
	@UiField Button createPersonOverlay;
	
	// UiBinder Templates
	interface JSOverlayExampleUiBinder extends UiBinder<Widget, JSOverlayExample> {}
	private static JSOverlayExampleUiBinder uiBinder = GWT.create(JSOverlayExampleUiBinder.class);
	
	final String PERSON_SCRIPT = "person.js";
	
	// Create the UI and wait for user interaction
	public JSOverlayExample(){
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");
		
		// Inject the JavaScript
		ScriptInjector.fromUrl(GWT.getModuleBaseURL()+"../"+PERSON_SCRIPT).setWindow(ScriptInjector.TOP_WINDOW).setCallback(new Callback<Void, Exception>(){
			@Override
			public void onFailure(Exception reason) {
				Window.alert("Script injection failed");
			}
			@Override
			public void onSuccess(Void result) {
				createPerson.setText("Create Person");
				createPerson.setEnabled(true);
				createPersonOverlay.setText("Create Person");
				createPersonOverlay.setEnabled(true);
			}
		}).inject();
	}

	
	/*
	 * 
	 * CODE BELOW REFERS TO THE NON OVERLAY APPROACH
	 * (FOR OVERLAY APPROACH SEE LOWER DOWN)
	 * 
	 */
	
	

	/**
	 * The JavaScript person object for the first example
	 */
	JavaScriptObject person;	

	
	/**
	 * Handle pressing the change Career button in first example
	 */
	@UiHandler("changeCareer")
    void changeCareer(ClickEvent event){
		int choice = (int) Math.floor(Math.random()*10);
		changeCareer(person, careers[choice]);
		showPerson(person);
    }	
	
	/**
	 * JSNI method to change person's career in the first example.  
	 * 
	 * Note that it is in JSNI and we have to return a new JavaScript Object (see changeCareerOverly for comparison)
	 * 
	 * 
	 * @param person The person JavaScriptObject
	 * @param newCareer Their new career
	 * @return A new JavaScriptObject with updated career
	 */
	native JavaScriptObject changeCareer(JavaScriptObject person, String newCareer)/*-{
		person.changeCareer(newCareer);
	}-*/;


	// Create a JavaScriptObject person
	@UiHandler("createPerson")
    void createPerson(ClickEvent event){
		person = makePerson();
		showPerson(person);
    }
	
	

	/**
	 * JSNI method to get JavaScriptObject person's career
	 */
	private native String getCareer(JavaScriptObject person)/*-{
		return person.career;
	}-*/;

	/**
	 * JSNI method to get JavaScriptObject person's name
	 */
	private native String getName(JavaScriptObject person)/*-{
		return person.name;
	}-*/;
	

	/**
	 * JSNI method to create a JavaScriptObject person
	 * @return JavaScriptObject representation of a person
	 */
	private native JavaScriptObject makePerson()/*-{
   		return $wnd.createPerson();
   	}-*/;	

	
	/**
	 * Show details of a JavaScriptObject person
	 * 
	 * Note how we need to make method calls to get info, and that those method calls are JSNI calls
	 * 
	 * @param person
	 */
	void showPerson(JavaScriptObject person){
		if(details.isOpen())details.setOpen(false);
		name.setText("Name: "+getName(person));
		career.setText("Career: "+getCareer(person));
		description.setText("Details: "+getName(person)+" the "+getCareer(person));
		details.setOpen(true);
	}	

	
	
	
	
	
	
	
	/*
	 * 
	 * CODE BELOW REFERS TO THE  OVERLAY APPROACH
	 * (FOR NON OVERLAY APPROACH SEE ABOVE )
	 * 
	 */
		
	
	
	/**
	 * The JavaScript person Overlay for the second example.
	 */
	PersonJavaScriptOverlay personOverlay;

	
	/**
	 * Java method to change a person's career in the second example
	 * 
	 * Note that for the Overlay approach we can use Java, and we don't need to return a new JavaScript object
	 * (see changeCareer for a comparison)
	 * 
	 */
	@UiHandler("changeCareerOverlay")
    void changeCareerOverlay(ClickEvent event){
		int choice = (int) Math.floor(Math.random()*10);
		personOverlay = (PersonJavaScriptOverlay) personOverlay.changeCareer(careers[choice]);
		showPersonOverlay(personOverlay);
    }

	
	
	// Create a JavaScriptOverlay person
	@UiHandler("createPersonOverlay")
    void createPersonOverlay(ClickEvent event){
		personOverlay = makePersonOverlay();
		showPersonOverlay(personOverlay);
    }	
	
	
	
	/**
	 * JSNI method to create a JavaScriptOverlay of a person
	 * Note that the returned object is a Java object (compare to makePerson that returned an opaque JavaScriptObject)
	 * @return A Java JavaScript Overlay representation of a person
	 */
	private native PersonJavaScriptOverlay makePersonOverlay()/*-{
   		return $wnd.createPerson();
  	}-*/;	  

		
	/**
	 * Show details of a JavaScript Overlay person
	 * 
	 * Note how, compared to showPerson, the calls are to Java object methods, and looks more Java like.
	 * In the compiled code this method will look the same as showPerson, so there is no overhead.
	 * 
	 * @param person
	 */
	void showPersonOverlay(PersonJavaScriptOverlay person){
		if(detailsOverlay.isOpen())detailsOverlay.setOpen(false);
		nameOverlay.setText("Name: "+person.getName());
		careerOverlay.setText("Career: "+person.getCareer());
		descriptionOverlay.setText("Details: "+person.getDescription());
		detailsOverlay.setOpen(true);
	}	
}

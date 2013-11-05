/**
 * 
 */
package com.manning.gwtia.ch04.client.extend_existing_widget;

// The rest of these imports just relate to standard user interface aspects.
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

/**
 * This example shows how to create a widget directly from the DOM.
 * 
 * The example builds a simple canvas widget (although GWT 2.2. introduces a specific GWT
 * widget, we keep this example as it shows how to build a widget)
 * 
 */
public class ExtendExample extends Composite{

	/**
	 * A new ReportSizeLabel object - this is defined in ReportSizeLabel.java 
	 * and is an extension of the InlineLabel from GWT whose onLoad method is 
	 * overloaded to display a message to the user of the width and height.
	 */
	ReportSizeLabel theLabel;

	/**
	 * Creates the Widget.
	 * Sets up history handling, the GUI components, and button handling.
	 */
	public ExtendExample(){
		theLabel = new ReportSizeLabel("What's my size?");
		initWidget(theLabel);
		
		// Add a ClickHandler to the label - this should cause an exception to be raised
		// since ReportSizeLabel overides the addClickHandler method of InnerLabel to explicitly
		// prevent a ClickHandler being added
		theLabel.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Window.alert("You clicked me!");
			}			
		});
	}
}
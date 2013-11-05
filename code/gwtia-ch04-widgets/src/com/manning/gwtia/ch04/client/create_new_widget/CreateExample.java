/**
 * 
 */
package com.manning.gwtia.ch04.client.create_new_widget;

// The rest of these imports just relate to standard user interface aspects.
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

/**
 * This example shows how to create a widget directly from the DOM.
 * 
 * The example builds a simple canvas widget (although GWT 2.2. introduces a specific GWT
 * widget, we keep this example as it shows how to build a widget)
 * 
 */
public class CreateExample extends Composite{

	/**
	 * The GWTiACanvas widget that is used in this example
	 * It is defined in the GWTiACanvas.java file
	 */
	GWTiACanvas theCanvas;
	
	// Holds the background color of the canvas widget before the mouse entered it
	private String oldBackgroundColor;
	

	/**
	 * Creates the Widget.
	 * Sets up history handling, the GUI components, and button handling.
	 */
	public CreateExample(){
		// Create the Canvas widget and show it
		theCanvas = new GWTiACanvas();
		theCanvas.setPixelSize(400, 400);
		// Put a black border around the canvas so the user can see when mouse is over or out of it
		// (normally would do this through CSS style sheets, but let's be explicit here)
		theCanvas.getElement().getStyle().setProperty("border", "1px solid black");
		// Set the canvas widget as the widget used by this composite
		initWidget(theCanvas);

		
		// Put a rectangle on the Canvas
		theCanvas.fillStyle("rgb(80,255,80)");
		theCanvas.fillRect(10, 20, 100, 50);
		
		
		// Add a mouse over handler to change the background colour of the canvas when the mouse is over it.
		// We also store the old background color so that we can restore it when the mouse is out
		theCanvas.addMouseOverHandler(new MouseOverHandler(){
		   public void onMouseOver(MouseOverEvent event){
			   oldBackgroundColor = theCanvas.getElement().getStyle().getBackgroundColor();
			   theCanvas.getElement().getStyle().setBackgroundColor("lightblue");
		   }
		});
		
		// Add a mouse out handler to restore the background color of the widget once the mouse leaves it.
		theCanvas.addMouseOutHandler(new MouseOutHandler(){
			   public void onMouseOut(MouseOutEvent event){
				   theCanvas.getElement().getStyle().setBackgroundColor(oldBackgroundColor);
			   }
			}
		);	
		
		theCanvas.addDomHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Clicked!");
			}}, ClickEvent.getType());
	}
}
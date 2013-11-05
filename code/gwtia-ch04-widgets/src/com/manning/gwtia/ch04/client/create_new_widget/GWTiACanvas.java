/**
 * 
 */
package com.manning.gwtia.ch04.client.create_new_widget;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Our GWTiA Canvas widget used in Chapter 4 of GWTiA
 * (GWT 2.2 introduced a GWT specific Canvas widget, but we keep this for the purpose of the book)
 *
 * The widget shows how to create a new widget from the DOM - it doesn't include any error handling
 * for browsers that do not support the Canvas element.
 * 
 * It is a very minimal implementation, and provides three methods for handling a Canvas
 * 
 * <ul>
 *    <li>getCanvasContext - to return the context of the Canvas as a JavaScript object</li>
 *    <li>fillStyle - to set the fill style to be used in the next canvas action</li>
 *    <li>fillRect - fills a rectangle based on the parameteres given</li>
 * </ul>
 *
 */
public class GWTiACanvas extends Widget implements HasMouseOverHandlers, HasMouseOutHandlers{

	/**
	 * The context of the Canvas, as a JavaScriptObject 
	 * (see Chapter 09 for details of what a JavaScriptObject is, for now you can just
	 * read this as a normal Java object that has type JavaScriptObject)
	 * 
	 */
	protected JavaScriptObject context;
	
	/**
	 * Constructor - Creates a DOM element of type Canvas and sets-up it's context.
	 */
	public GWTiACanvas(){
		// Create a GWT DOM Element in memory
		Element element = Document.get().createElement("canvas");
		// Call helper method to set up widget
		setUpWidget(element);
	}
	
	/**
	 * Construct a GWTiACanvas object from a canvas DOM element that already exists
	 * @param element
	 */
	public GWTiACanvas(Element element){
		// Check the Element is a canvas DOM element
		assert element.getTagName().equalsIgnoreCase("canvas");
		// Call helper method to set up widget
		setUpWidget(element);
	}
	
	/**
	 * Wrap an existing DOM element on the HTML page as a canvas widget
	 * @param element
	 * @return
	 */
	public GWTiACanvas wrap(Element element) {
		// Assert that the element is on the DOM page already
		assert Document.get().getBody().isOrHasChild(element);
		// Create a new GWTiACanvas widget from that element
		GWTiACanvas canvas = new GWTiACanvas(element);
		// Call the onAttach method to make sure it is attached properly into GWT
		canvas.onAttach();
		// Add the widget to the list of widgets GWT needs to clean up when the application closes
		RootPanel.detachOnWindowClose(canvas);
		// Return the new GWTiACanvas widget
		return canvas;
	}

	
	/**
	 * Helper method to set up the widget
	 * @param element The DOM element used for the Canvas
	 */
	protected void setUpWidget(Element element){
		// Set this widget's Element to be the canvas element we have just created
	    setElement(element);
	    // Set a style name for the widget; this can follow any naming convention you
	    // have on your project - we have just called it canvas-style for now.
	    // As this is a widget you can call the SetStyleName method to change at a later date.
	    setStyleName("canvas-style");
	    // Set the context variable with the Canvas' context.  This is not a necessary widget step, rather
	    // it is a specific step for the Canvas as the context is used to draw items on the canvas
	    context = getCanvasContext(element);		
	}
	
	/**
	 * Allow a MouseOutHandler to be added to this widget. 
	 * (see chapter 05 on events for more details of what is going on here if you are interested - 
	 * for now you just need to now that in CreateExample.java we will call this method, passing in a
	 * MouseOutHandler that is called when the mouse leaves the widget to restore the background color)
	 * 
	 */
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
	     return this.addDomHandler(handler, MouseOutEvent.getType());
	}

	/**
	 * Allow a MouseOverHandler to be added to this widget. 
	 * (see chapter 05 on events for more details of what is going on here if you are interested - 
	 * for now you just need to now that in CreateExample.java we will call this method, passing in a
	 * MouseOverHandler that is called when the mouse enters the widget to change the background color)
	 * 
	 */
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
	     return this.addDomHandler(handler, MouseOverEvent.getType());
	}

	/**
	 * Draws a rectangle on the canvas starting at a defined top-left corner and having a given width and height.
	 * The method itself is written in JSNI as we have to access JavaScript to implement the functionality.
	 * (see Chapter 09 for more on JSNI - for now, think of this as nothing more than a Java method that calls
	 * a JavaScript function defined in the browser).
	 * 
	 * @param top y-position of the top left corner of the rectangle to be drawn
	 * @param left x-position of the top left corner of the rectangle to be drawn
	 * @param width width of the rectangle to be drawn
	 * @param height height of the rectangle to be drawn
	 */
	public native void fillRect(int top, int left, int width, int height)/*-{
		this.@com.manning.gwtia.ch04.client.create_new_widget.GWTiACanvas::context.fillRect(top,left,width,height);
	}-*/;

	/**
	 * Sets the fillStyle that will be used in the next drawing action on the canvas.
	 * The method itself is written in JSNI as we have to access JavaScript to implement the functionality.
	 * (see Chapter 09 for more on JSNI - for now, think of this as nothing more than a Java method that calls
	 * a JavaScript function defined in the browser).
	 * @param fillStyle the fill style to use.
	 */
	public native void fillStyle(String fillStyle)/*-{
		this.@com.manning.gwtia.ch04.client.create_new_widget.GWTiACanvas::context.fillStyle = fillStyle;
		}-*/;

	/**
	 * Gets the 2d context of the Canvas element - this context is used to draw items to the canvas.
	 * The method itself is written in JSNI as we have to access JavaScript to implement the functionality.
	 * It returns a JavaScriptObject representing the context.
	 * (see Chapter 09 for more on JSNI and JavaScriptObjects - for now, think of this as nothing more than a Java method that calls
	 * a JavaScript function defined in the browser; the returnin JavaScriptObject is a Java object that represents the 
	 * JavaScript object returned by the JavaScript call).
	 * @param element The DOM element that hte context is to be retrieved for.
	 * @return A JavaScriptObject that represents the canvas context.
	 */
	protected native JavaScriptObject getCanvasContext(Element element)/*-{
		return element.getContext('2d');
	}-*/;
}

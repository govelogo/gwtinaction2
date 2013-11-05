package com.manning.gwtia.ch14.client.eventPreviewing;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class EventPreviewingExample extends ResizeComposite{
	
    private static EventPreviewingExampleUiBinder uiBinder = GWT.create(EventPreviewingExampleUiBinder.class);
    interface EventPreviewingExampleUiBinder extends UiBinder<Widget, EventPreviewingExample> {}
    
    
    @UiField FlowPanel comment; 
    @UiField ScrollPanel scroller;
    @UiField FocusPanel outer;
    @UiField Label previewingStatus;
    final String BASE_COMMENT = "Event: ";

	HashMap<Integer, String> eventTypes = new HashMap<Integer, String>();
    
	public EventPreviewingExample(){
		initWidget(uiBinder.createAndBindUi(this));
        this.setSize("100%", "100%");
        
        // Set up some nice Strings to show what the event is
        eventTypes.put(1, "Mouse Click");
        eventTypes.put(2, "Mouse Double Click");
        eventTypes.put(4, "Mouse Down");
        eventTypes.put(8, "Mouse Up");
        eventTypes.put(16, "Mouse Over");
        eventTypes.put(32, "Mouse Out");
        eventTypes.put(64, "Mouse Move");
        eventTypes.put(512, "Key Up");
        eventTypes.put(128, "Key Down");
        eventTypes.put(256, "Key Press");

	}
	
	/**
	 * A HandlerRegistration for the native preview handler.
	 * We need to store this when we add the handler so that
	 * we can remove it at a later point.
	 */
	HandlerRegistration previewerHandler;
	
	/**
	 * Define the NativePreviewHandler that will be added/removed from the Event
	 * object depending upon whether the user is in the applications yellow 
	 * panel or not
	 */
	NativePreviewHandler previewer = new NativePreviewHandler(){
		@Override
		public void onPreviewNativeEvent(NativePreviewEvent event) {
			// Attempt to get a nice description of the event
			String eventType = null;
			Integer et = event.getTypeInt(); 
			eventType = eventTypes.get(et);
			if (eventType==null) eventType= event.getNativeEvent().getString();
			
			// Display the event in the comment window of the application
			Label theEvent = new Label(counter++ + " "+BASE_COMMENT+ eventType);
	    	comment.add(theEvent);
	    	// Make sure the latest event description is visible
	    	scroller.ensureVisible(theEvent);
		}
	};
	
	/**
	 * Add the NativePreviewHandler when the mouse is over the application's
	 * area of interest.
	 * We do this so that the system is more efficient and not previewing all
	 * events all of the time.
	 * @param event a MouseOver event
	 */
    @UiHandler("wrapper")
    void startPreviewingEvents (MouseOverEvent event){
    	previewerHandler = Event.addNativePreviewHandler(previewer);
    	previewingStatus.setText("Previewing Events: ON");
    }

    /**
     * Remove the NativePreviewHandler when the mouse leaves the application's
     * area of interest.
     * @param event
     */
    @UiHandler("wrapper")
    void stopPreviewingEvent (MouseOutEvent event){
    	previewerHandler.removeHandler();
    	previewingStatus.setText("Previewing Events: OFF");
    }

    
    /**
     * When the clear button is pressed, we just simply clear the comment window.
     * @param event
     */
    @UiHandler("clear")
    void clearInformation (ClickEvent event){
    	comment.clear();
    }

    /**
     * A counter that is added to the event string displayed so we can follow the ordering
     */
    int counter = 1;

}

package com.manning.gwtia.ch14.client.eventHandling;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

public class EventHandlingExample extends ResizeComposite{
	
    private static EventHandlingExampleUiBinder uiBinder = GWT.create(EventHandlingExampleUiBinder.class);
    interface EventHandlingExampleUiBinder extends UiBinder<Widget, EventHandlingExample> {}

    @UiField FlowPanel comment;
    @UiField(provided=true) ToggleButton bubble;
    final String BASE_COMMENT = "Event Fired on panel: ";
	
	public EventHandlingExample(){
        bubble = new ToggleButton("Click to Turn OFF event bubbling","Click to Turn ON event bubbling");
		initWidget(uiBinder.createAndBindUi(this));
        this.setSize("100%", "100%");
	}
	
    @UiHandler("outer")
    void showOuterEventHandling (ClickEvent event){
    	handleEvent("red",event);
    }
    

    @UiHandler("inner")
    void showInnerEventHandling (ClickEvent event){
    	handleEvent("blue", event);
    }
    
    @UiHandler("innermost")
    void showInnermostEventHandling (ClickEvent event){
    	handleEvent("green", event);
    }
    
    @UiHandler("clear")
    void clearInformation (ClickEvent event){
    	comment.clear();
    }
    
    boolean bubbleEvents = true;
    
    @UiHandler("bubble")
    void bubbleOrNot(ClickEvent event){
    	if(bubble.isDown()) bubbleEvents=false;
    	else bubbleEvents=true;
    }
    
    int counter = 1;
    
    Timer resetCounter = new Timer() {
        @Override
        public void run() {
        	counter = 1;
        }
      };
    
    private void handleEvent(String panelName, ClickEvent event){
    	comment.add(new Label(counter++ + ". "+BASE_COMMENT+panelName));
    	if(!bubbleEvents) event.stopPropagation();
    	resetCounter.schedule(10);
    }
}

package com.manning.gwtia.ch14.client.eventSinking;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class EventSinkingExample extends ResizeComposite{
	
    private static EventSinkingExampleUiBinder uiBinder = GWT.create(EventSinkingExampleUiBinder.class);
    interface EventSinkingExampleUiBinder extends UiBinder<Widget, EventSinkingExample> {}
    
    
    @UiField MyWidget example;
    @UiField Button changeText;
    @UiField FlowPanel comment;
    @UiField ScrollPanel scroller;


    String text = "Some Text: ";
    int counter = 100;
    final String BASE_COMMENT = "Event: ";
    
	public EventSinkingExample(){
		initWidget(uiBinder.createAndBindUi(this));
        this.setSize("100%", "100%");
        example.setText(text + counter++);
	}

	
	@UiHandler("example")
	void handleClick(ClickEvent click){
		addMessage("You clicked MyWidget");
	}
	
	@UiHandler("example")
	void handleChangeText(ValueChangeEvent<String> event){
		addMessage("Text has been changed!");
	}
	
	@UiHandler("example")
	void handleDragStart(DragStartEvent event){
		addMessage("Starting a drag!");
	}
	
	private void addMessage(String msg){
		Label lbl = new Label(msg);
		comment.add(lbl);
		scroller.ensureVisible(lbl);
	}
	
	@UiHandler("changeText")
	void handleChangeText(ClickEvent click){
		example.setText(text+counter++);
	}
	
	@UiHandler("clear")
	void clearScroller(ClickEvent click){
		comment.clear();
	}
	
}

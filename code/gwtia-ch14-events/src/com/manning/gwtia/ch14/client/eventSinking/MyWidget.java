package com.manning.gwtia.ch14.client.eventSinking;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDragStartHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class MyWidget extends Widget implements HasDragStartHandlers, HasClickHandlers, HasValueChangeHandlers<String>{

	public MyWidget(){
		setElement(Document.get().createDivElement());
	}
	
	public void setText(String text){
		this.getElement().setInnerHTML(text);
		ValueChangeEvent.fire(this, text);

	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
	
	@Override
	public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
		return this.addBitlessDomHandler(handler, DragStartEvent.getType());
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
}

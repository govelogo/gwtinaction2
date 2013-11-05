package com.manning.gwtia.ch14.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ChangeColorEvent extends GwtEvent<ChangeColorEventHandler>{
	public static final Type<ChangeColorEventHandler> TYPE = new Type<ChangeColorEventHandler>();

	private String color;

	  public ChangeColorEvent(String color) {
	    this.color=color;
	  }



	  public String getColor() {
		return color;
	}



	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChangeColorEventHandler> getAssociatedType() {
	    return TYPE;
	}

	@Override
	protected void dispatch(ChangeColorEventHandler handler) {
		handler.onChangeColorSent(this);
		
	}
}





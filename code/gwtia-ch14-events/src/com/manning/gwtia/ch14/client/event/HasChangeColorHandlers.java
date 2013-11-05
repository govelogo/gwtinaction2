package com.manning.gwtia.ch14.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasChangeColorHandlers extends HasHandlers{
	public HandlerRegistration addHasChangeColorHandler(ChangeColorEventHandler handler);
}

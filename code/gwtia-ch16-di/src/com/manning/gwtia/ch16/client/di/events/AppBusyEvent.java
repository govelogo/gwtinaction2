package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.GwtEvent;
import com.manning.gwtia.ch16.client.di.events.AppBusyEvent;
import com.manning.gwtia.ch16.client.di.events.AppBusyHandler;
import com.manning.gwtia.ch16.client.di.events.HasAppBusyHandlers;

public class AppBusyEvent extends
		GwtEvent<AppBusyHandler> {
	private static Type<AppBusyHandler> TYPE = new Type<AppBusyHandler>();

	public AppBusyEvent(){}
	
	/**
	 * Gets the event type associated with load events.
	 * 
	 * @return the handler type
	 */
	public static com.google.gwt.event.shared.GwtEvent.Type<AppBusyHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppBusyHandler handler) {
		handler.onAppBusyEvent(this);
	}

	protected void fire(HasAppBusyHandlers source) {
		source.fireEvent(new AppBusyEvent());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AppBusyHandler> getAssociatedType() {
		return TYPE;
	}
}

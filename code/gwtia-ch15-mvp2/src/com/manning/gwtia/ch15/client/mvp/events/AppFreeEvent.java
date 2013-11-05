package com.manning.gwtia.ch15.client.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class AppFreeEvent extends
		GwtEvent<AppFreeHandler> {
	private static Type<AppFreeHandler> TYPE = new Type<AppFreeHandler>();

	public AppFreeEvent(){}
	
	/**
	 * Gets the event type associated with load events.
	 * 
	 * @return the handler type
	 */
	public static com.google.gwt.event.shared.GwtEvent.Type<AppFreeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppFreeHandler handler) {
		handler.onAppFreeEvent(this);
	}

	protected void fire(HasAppFreeHandlers source) {
		source.fireEvent(new AppFreeEvent());
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AppFreeHandler> getAssociatedType() {
		return TYPE;
	}
}

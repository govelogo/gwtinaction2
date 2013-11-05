package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.GwtEvent;
import com.manning.gwtia.ch16.client.di.events.HasTagDeletedHandlers;
import com.manning.gwtia.ch16.client.di.events.TagDeletedEvent;
import com.manning.gwtia.ch16.client.di.events.TagDeletedHandler;

public class TagDeletedEvent extends
		GwtEvent<TagDeletedHandler> {
	private static Type<TagDeletedHandler> TYPE = new Type<TagDeletedHandler>();

	public TagDeletedEvent(){}
	
	public TagDeletedEvent(String value){
		this.value = value;
	}
	
	/**
	 * Gets the event type associated with load events.
	 * 
	 * @return the handler type
	 */
	public static com.google.gwt.event.shared.GwtEvent.Type<TagDeletedHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TagDeletedHandler handler) {
		handler.onTagDeleted(this);
	}

	protected void fire(HasTagDeletedHandlers source) {
		source.fireEvent(new TagDeletedEvent());
	}
	
	public static void fire(HasTagDeletedHandlers source, String tag) {
		source.fireEvent(new TagDeletedEvent(tag));
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TagDeletedHandler> getAssociatedType() {
		return TYPE;
	}
	
	private String value;

	public String getTag() {
		return value;
	}
}

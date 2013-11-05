package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.EventHandler;
import com.manning.gwtia.ch16.client.di.events.TagDeletedEvent;

public interface TagDeletedHandler extends EventHandler{
	  public void onTagDeleted(TagDeletedEvent event);
}

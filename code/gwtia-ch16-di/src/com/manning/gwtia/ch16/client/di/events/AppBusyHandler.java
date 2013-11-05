package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.EventHandler;
import com.manning.gwtia.ch16.client.di.events.AppBusyEvent;

public interface AppBusyHandler extends EventHandler{
	  public void onAppBusyEvent(AppBusyEvent event);
}

package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.EventHandler;
import com.manning.gwtia.ch16.client.di.events.AppFreeEvent;

public interface AppFreeHandler extends EventHandler{
	  public void onAppFreeEvent(AppFreeEvent event);
}

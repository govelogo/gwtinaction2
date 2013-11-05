package com.manning.gwtia.ch15.client.mvp.events;

import com.google.gwt.event.shared.EventHandler;

public interface AppBusyHandler extends EventHandler{
	  public void onAppBusyEvent(AppBusyEvent event);
}

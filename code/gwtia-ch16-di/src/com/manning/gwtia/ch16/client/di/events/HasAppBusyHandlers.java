package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.manning.gwtia.ch16.client.di.events.AppBusyHandler;


public interface HasAppBusyHandlers extends HasHandlers {
  public HandlerRegistration addHasAppBusyHandler(AppBusyHandler handler); 
}

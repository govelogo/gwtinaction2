package com.manning.gwtia.ch16.client.di.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.manning.gwtia.ch16.client.di.events.AppFreeHandler;


public interface HasAppFreeHandlers extends HasHandlers {
  public HandlerRegistration addHasAppFreeHandler(AppFreeHandler handler); 
}

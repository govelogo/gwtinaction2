package com.manning.gwtia.ch02.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloWorld implements EntryPoint {

	@Override
	public void onModuleLoad(){
		Label theGreeting = new Label("Hello World!");
		RootPanel.get().add(theGreeting);
	}
}

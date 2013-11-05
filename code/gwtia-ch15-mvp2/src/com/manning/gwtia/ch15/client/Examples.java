package com.manning.gwtia.ch15.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Examples implements EntryPoint
{
	
	ExamplePanel examplePanel;
	
    public void onModuleLoad ()
    {
    	examplePanel = new ExamplePanel();
        RootPanel.get().add(examplePanel, 0, 0);
    }    
}

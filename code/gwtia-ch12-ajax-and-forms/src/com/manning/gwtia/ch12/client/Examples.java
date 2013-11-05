package com.manning.gwtia.ch12.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class Examples implements EntryPoint
{

    @Override
    public void onModuleLoad ()
    {
        RootPanel.get().add(new ExamplePanel(), 0, 0);
    }
}

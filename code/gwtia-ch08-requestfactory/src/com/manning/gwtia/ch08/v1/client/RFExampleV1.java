package com.manning.gwtia.ch08.v1.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class RFExampleV1 implements EntryPoint
{
    @Override
    public void onModuleLoad ()
    {
        RootPanel.get().add(new TestPanel());
    }
}

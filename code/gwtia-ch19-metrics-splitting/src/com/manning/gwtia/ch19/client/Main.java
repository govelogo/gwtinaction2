package com.manning.gwtia.ch19.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.manning.gwtia.ch19.client.car.CarGateway;
import com.manning.gwtia.ch19.client.car.CarGateway.Callback;


public class Main implements EntryPoint
{
    private CarGateway carGateway;



    public void onModuleLoad ()
    {
        RootPanel.get().add(new Label("Loading car data..."));

        CarGateway.createAsync(new Callback()
        {
            public void onCreated (CarGateway gateway)
            {
                carGateway = gateway;
                initDisplay();
            }

            public void onCreateFailed (Throwable reason)
            {
                Window.alert("Error loading data");
            }
        });
    }

    private void initDisplay ()
    {
        RootPanel.get().clear();
        Button button = new Button("Start your engines!");
        RootPanel.get().add(button);
        button.addClickHandler(new ClickHandler()
        {
            public void onClick (ClickEvent event)
            {
                carGateway.startEngine();
            }
        });
    }
}

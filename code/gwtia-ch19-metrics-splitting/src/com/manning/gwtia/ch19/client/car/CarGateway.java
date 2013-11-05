package com.manning.gwtia.ch19.client.car;


import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class CarGateway
{
    private CarGateway () { }

    public static void createAsync (final Callback callback)
    {
        GWT.runAsync(CarGateway.class, new RunAsyncCallback()
        {
            public void onSuccess ()
            {
                callback.onCreated(new CarGateway());
            }

            public void onFailure (Throwable reason)
            {
                callback.onCreateFailed(reason);
            }
        });
    }

    public interface Callback
    {
        void onCreated (CarGateway gateway);

        void onCreateFailed (Throwable reason);
    }

    private boolean running;
    public void startEngine ()
    {
        if (! running) {
            RootPanel.get().add(new CheckBox("A Checkbox"));
            RootPanel.get().add(new DatePicker());
            RootPanel.get().add(new RichTextArea());
            running = true;
        }
    }

    public void setSpeed (int mph)
    {
    }

    public int getSpeed ()
    {
        return 0;
    }

    public void turnLeft (int degrees)
    {
        RootPanel.get().add(new Label("Left " + degrees));
    }

    public void turnRight (int degrees)
    {
        RootPanel.get().add(new Label("Right " + degrees));
    }

    public int getHeading ()
    {
        return 0;
    }
}

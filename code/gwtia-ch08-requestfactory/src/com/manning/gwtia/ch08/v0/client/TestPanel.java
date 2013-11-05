package com.manning.gwtia.ch08.v0.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class TestPanel extends Composite
{
    interface Binder extends UiBinder<Widget, TestPanel> { }
    static Binder uiBinder = GWT.create(Binder.class);

    Logger log = Logger.getLogger("");
    @UiField TextBox txtInput;

    public TestPanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
    }
}

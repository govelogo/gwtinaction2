package com.manning.gwtia.ch08.v1.client.ui;


import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContactsPanel extends Composite
{
    interface Binder extends UiBinder<Widget, ContactsPanel> {}

    private static Binder uiBinder = GWT.create(Binder.class);
    private static Logger log = Logger.getLogger(ContactsPanel.class.getName());
    
    @UiField Button btnAddContact;
    
    public ContactsPanel ()
    {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    @UiHandler("btnAddContact")
    public void onAddContact (ClickEvent event)
    {
        ContactEditor dialog = new ContactEditor();
        dialog.addCloseHandler(new CloseHandler<PopupPanel>()
        {
            @Override
            public void onClose (CloseEvent<PopupPanel> event)
            {
                log.info("popup was closed");
            }
        });
        dialog.center();
    }
}

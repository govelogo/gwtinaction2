package com.manning.gwtia.ch08.v1.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ContactEditor extends DialogBox
{
    interface Binder extends UiBinder<Widget, ContactEditor> {}
    private static Binder uiBinder = GWT.create(Binder.class);

    @UiField TextBox name;
    @UiField TextBox email;
    @UiField TextBox phone;
    @UiField TextArea notes;

    
    public ContactEditor ()
    {
        setWidget(uiBinder.createAndBindUi(this));
        setText("Add Contact");
        setGlassEnabled(true);
    }
    
    @UiHandler("btnAdd")
    public void onAdd (ClickEvent event)
    {
        // TODO... Add RequestFactory code here.
//        ContactProxy contact = new ContactProxy();
//        contact.setName(name.getText());
//        contact.setEmail(email.getText());
//        contact.setPhone(phone.getText());
//        contact.setNotes(notes.getText());
    }

    @UiHandler("btnCancel")
    public void onCancel (ClickEvent event)
    {
        hide();
    }
}

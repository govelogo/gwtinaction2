package com.manning.gwtia.ch08.v2.client;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.manning.gwtia.ch08.v2.server.Contact.Phone;

@ProxyFor(Phone.class)
public interface PhoneProxy extends ValueProxy
{
    String getType ();
    void setType (String type);
    String getNumber ();
    void setNumber (String number);
}

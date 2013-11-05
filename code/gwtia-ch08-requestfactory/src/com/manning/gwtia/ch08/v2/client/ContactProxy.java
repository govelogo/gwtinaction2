package com.manning.gwtia.ch08.v2.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.manning.gwtia.ch08.v2.server.Contact;
import com.manning.gwtia.ch08.v2.server.ContactLocator;

@ProxyFor(value = Contact.class, locator = ContactLocator.class)
public interface ContactProxy extends EntityProxy
{
    Long getId ();

    String getName ();
    void setName (String text);
    
    String getEmail ();
    void setEmail (String text);
    
    List<PhoneProxy> getPhones ();
    void setPhones (List<PhoneProxy> phones);

    String getNotes ();
    void setNotes (String text); 
}

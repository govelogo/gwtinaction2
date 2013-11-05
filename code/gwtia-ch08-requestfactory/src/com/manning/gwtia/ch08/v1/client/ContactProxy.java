package com.manning.gwtia.ch08.v1.client;

import java.util.List;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.manning.gwtia.ch08.v1.server.Contact;
import com.manning.gwtia.ch08.v1.server.Contact.Phone;

@ProxyFor(value = Contact.class)
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
    
    @ProxyFor(Phone.class)
    public interface PhoneProxy extends ValueProxy
    {
        String getType ();
        void setType (String type);
        String getNumber ();
        void setNumber (String number);
    }
}

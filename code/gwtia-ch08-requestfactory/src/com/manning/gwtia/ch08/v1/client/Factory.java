package com.manning.gwtia.ch08.v1.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.manning.gwtia.ch08.v1.server.Contact;


public interface Factory extends RequestFactory
{
    ContactRequest createContactRequest ();


    @Service(value = Contact.class)
    public interface ContactRequest extends RequestContext
    {
        Request<Integer> count ();
        Request<ContactProxy> find (Long id);
        Request<List<ContactProxy>> findAllContacts ();

        InstanceRequest<ContactProxy, Void> persist ();
        InstanceRequest<ContactProxy, Void> remove ();
    }
}

package com.manning.gwtia.ch08.v2.client;


import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.manning.gwtia.ch08.v2.server.ContactService;
import com.manning.gwtia.ch08.v2.server.ContactServiceLocator;

public interface Factory extends RequestFactory
{
    ContactRequest createContactRequest ();

    @Service(value = ContactService.class, locator = ContactServiceLocator.class)
    public interface ContactRequest extends RequestContext
    {
        Request<Integer> count();
        Request<ContactProxy> find(Long id);
        Request<List<ContactProxy>> findAllContacts();
        Request<Void> persist(ContactProxy contact);
        Request<Void> remove(ContactProxy contact);
    }
}

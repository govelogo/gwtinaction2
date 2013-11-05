package com.manning.gwtia.ch08.v2.server;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * The Class ContactLocator.
 */
public class ContactLocator extends Locator<Contact, Long>
{

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#create(java.lang.Class)
     */
    @Override
    public Contact create (Class<? extends Contact> clazz)
    {
        return new Contact();
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#find(java.lang.Class, java.lang.Object)
     */
    @Override
    public Contact find (Class<? extends Contact> clazz, Long id)
    {
        return CEM.fetch(id);
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#getDomainType()
     */
    @Override
    public Class<Contact> getDomainType ()
    {
        return Contact.class;
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#getId(java.lang.Object)
     */
    @Override
    public Long getId (Contact contact)
    {
        return contact.getId();
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#getIdType()
     */
    @Override
    public Class<Long> getIdType ()
    {
        return Long.class;
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.Locator#getVersion(java.lang.Object)
     */
    @Override
    public Object getVersion (Contact contact)
    {
        return new Date(); //contact.getVersion();
    }

}

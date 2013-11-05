package com.manning.gwtia.ch09.server;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * The Class ContactLocator.
 */
public class ContactLocator extends Locator<Contact, Long> implements ServiceLocator
{

    private static ContactService serviceInstance;


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
        return getServiceInstance().find(id);
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
        return contact.getVersion();
    }

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.ServiceLocator#getInstance(java.lang.Class)
     */
    @Override
    public Object getInstance (Class<?> clazz)
    {
        return ContactLocator.getServiceInstance();
    }

    private static ContactService getServiceInstance ()
    {
        if (serviceInstance == null)
            serviceInstance = new ContactService();
        return serviceInstance;
    }

}

package com.manning.gwtia.ch08.v2.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * The Class ContactServiceLocator.
 */
public class ContactServiceLocator implements ServiceLocator
{
    
    /** The service instance. */
    private static ContactService serviceInstance;

    /* (non-Javadoc)
     * @see com.google.web.bindery.requestfactory.shared.ServiceLocator#getInstance(java.lang.Class)
     */
    @Override
    public Object getInstance (Class<?> clazz)
    {
        return ContactServiceLocator.getServiceInstance();
    }

    /**
     * Gets the service instance.
     *
     * @return the service instance
     */
    private static ContactService getServiceInstance ()
    {
        if (serviceInstance == null)
            serviceInstance = new ContactService();
        return serviceInstance;
    }
}

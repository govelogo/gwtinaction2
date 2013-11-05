package com.manning.gwtia.ch08.v2.server;

import java.util.List;

public class ContactService
{

    public Integer count() { return CEM.list().size(); }

    public Contact find(Long id) { return CEM.fetch(id); }

    public List<Contact> findAllContacts() { return CEM.list(); };

    public void persist(Contact c) { CEM.persist(c); }

    public void remove(Contact c) { CEM.delete(c.getId()); }

}

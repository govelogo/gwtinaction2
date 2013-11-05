package com.manning.gwtia.ch09.server;

import java.util.List;

import com.manning.gwtia.ch09.server.Contact.Phone;


public class ContactService
{

    public Integer count() { return CEM.list().size(); }

    public Contact find(Long id) { return CEM.fetch(id); }

    public List<Contact> findAllContacts() { return CEM.list(); };

    public Contact persist(Contact c) { 
    	return CEM.persist(c);
    }

    public void remove(Contact c) { CEM.delete(c.getId()); }
    
    public static Phone findPhone(Long id) {
        return null;
    }
      
 	public static List<Phone> phoneList (Long contactId) {
  		return CEM.phoneList(contactId);
  	}

	public static void persist(Phone phone) {
		CEM.persist(phone);
    }
  

}

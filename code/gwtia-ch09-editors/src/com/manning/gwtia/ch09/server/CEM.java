package com.manning.gwtia.ch09.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.manning.gwtia.ch09.server.Contact.Phone;


public class CEM
{
  static ConcurrentHashMap<Long, Contact> entities = new ConcurrentHashMap<Long, Contact>();
  static ConcurrentHashMap<Long, Phone> phoneEntities = new ConcurrentHashMap<Long, Phone>();

  static AtomicLong index = new AtomicLong();
  static AtomicLong phoneIndex = new AtomicLong();




  public static Contact persist (Contact entity) {
    if (entity.getId() == null) entity.setId(index.incrementAndGet());
    if (entity.getVersion() == null)
      entity.setVersion(1);
    else
      entity.setVersion(entity.getVersion() + 1);
    entities.put(entity.getId(), entity);
    return entity;
  }

  public static Contact fetch (Long id) {
    Contact contact = entities.get(id);
    if (contact == null) return null;
    return copy(entities.get(id));
  }

  public static boolean delete (Long id) {
	 for (Phone e : phoneEntities.values()){
	    	if(e.getContactId().longValue()==id.longValue()){
	    		phoneEntities.remove(e.getId());
	    	}
	 }
 
    return entities.remove(id) != null;
  }

  public static List<Contact> list () {
    ArrayList<Contact> result = new ArrayList<Contact>();
    for (Contact e : entities.values())
      result.add(copy(e));

    Collections.reverse(result);
    return result;
  }
  
  public static List<Phone> phoneList (Long contactId) {
	    ArrayList<Phone> result = new ArrayList<Phone>();
	    for (Phone e : phoneEntities.values()){
	    	if(e.getContactId().longValue()==contactId.longValue()){
	    		result.add(copyPhone(e));
	    	}
	    }
	    return result;
	  }

  private static Contact copy (Contact c) {
    Contact copy = new Contact();
    copy.setId(c.getId());
    copy.setVersion(c.getVersion());
    copy.setName(c.getName());
    copy.setEmail(c.getEmail());
    copy.setPhones(c.getPhones());
    copy.setNotes(c.getNotes());
    return copy;
  }
  
  private static Phone copyPhone(Phone c) {
	    Phone copy = new Phone();
	    copy.setId(c.getId());
	    copy.setContactId(c.getContactId());
	    copy.setVersion(c.getVersion());
	    copy.setType(c.getType());
	    copy.setNumber(c.getNumber());
	    return copy;
 }

  public static Phone findPhone(Long id) {
	  return copyPhone(phoneEntities.get(id));
  }
  public static void persist(Phone phone) {
    if (phone.getId() == null) phone.setId(Long.valueOf( phoneIndex.incrementAndGet()));
    if (phone.getVersion() == null)
    	phone.setVersion(1);
    else
    	phone.setVersion(phone.getVersion() + 1);
    phoneEntities.put(phone.getId(), phone);
	
  }
  
}

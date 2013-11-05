package com.manning.gwtia.ch08.v2.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class CEM
{
  static ConcurrentHashMap<Long, Contact> entities = new ConcurrentHashMap<Long, Contact>();
  static AtomicLong index = new AtomicLong();



  public static void persist (Contact entity) {
    if (entity.getId() == null) entity.setId(index.incrementAndGet());
    if (entity.getVersion() == null)
      entity.setVersion(1);
    else
      entity.setVersion(entity.getVersion() + 1);
    entities.put(entity.getId(), entity);
  }

  public static Contact fetch (Long id) {
    Contact contact = entities.get(id);
    if (contact == null) return null;
    return copy(entities.get(id));
  }

  public static boolean delete (Long id) {
    return entities.remove(id) != null;
  }

  public static List<Contact> list () {
    ArrayList<Contact> result = new ArrayList<Contact>();
    for (Contact e : entities.values())
      result.add(copy(e));
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
}

package com.manning.gwtia.ch09.server;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Contact 
{
    private Long id;
    private Integer version;
    @Size(min = 2, max = 20) private String name;
    @Pattern(regexp = "[^@\\s]+@[^@\\s]+") private String email;
    private List<Phone> phones;
    @Size(max = 100) private String notes;


    public Long getId ()
    {
        return id;
    }

    public Integer getVersion ()
    {
        return version;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    public void setVersion (Integer version)
    {
        this.version = version;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public List<Phone> getPhones ()
    {
        return phones;
    }

    public void setPhones (List<Phone> phones)
    {
        this.phones = phones;
    }

    public String getNotes ()
    {
        return notes;
    }

    public void setNotes (String notes)
    {
        this.notes = notes;
    }

     public static class Phone
    {
          private Long id;
        
          private Long contactId;
          
		private Integer version;
    	
          private String type;
          private String number;

          public Long getId() {
        	  return id;
          }

          public void setId(Long id) {
        	  this.id = id;
          }
       	  public Long getContactId() {
  			return contactId;
  		}

  		public void setContactId(Long contactId) {
  			this.contactId = contactId;
  		}

          public Integer getVersion() {
        	  return version;
          }

          public void setVersion(Integer version) {
        	  this.version = version;
          }

          public String getType () {
        	  return type;
          }

          public void setType (String type) {
        	  this.type = type;
          }

          public String getNumber () {
        	  return number;
          }

          public void setNumber (String number) {
        	  this.number = number;
          }
          
      	public static Phone findPhone(Long id){
    		return ContactService.findPhone(id);
    	}
      	
      	public static List<Phone> phoneList (Long contactId) {
      		return ContactService.phoneList(contactId);
      	}
      	 public void persist() {
      		 ContactService.persist(this);
      	 }
      	 public void copyFrom(Phone copyFrom) {
      	    number = copyFrom.getNumber();
      	    type = copyFrom.getType();
      	    id = copyFrom.getId();
      	    version = copyFrom.getVersion();
      	  }


    }
}
